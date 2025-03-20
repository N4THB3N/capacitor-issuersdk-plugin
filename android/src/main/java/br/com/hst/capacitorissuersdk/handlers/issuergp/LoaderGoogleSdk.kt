package br.com.hst.capacitorissuersdk.handlers.issuergp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import br.com.hst.capacitorissuersdk.model.issuergp.DefaultErrorResponse
import br.com.hst.capacitorissuersdk.model.issuergp.DefaultResponse
import br.com.hst.capacitorissuersdk.model.issuergp.ProvisioningDataPlugin
import br.com.hst.capacitorissuersdk.model.issuergp.ProvisioningEncDataPlugin
import br.com.hst.capacitorissuersdk.model.issuergp.ResultStatus
import br.com.hst.capacitorissuersdk.model.issuergp.TokenizeDataPlugin
import br.com.hst.capacitorissuersdk.utils.Constants
import br.com.hst.issuergp.core.IssuerGP
import br.com.hst.issuergp.core.IssuerGPUtil
import br.com.hst.issuergp.data.model.*
import br.com.hst.issuergp.data.model.IssuerGpStatusCode
import br.com.hst.issuergp.exception.IssuerGPException
import br.com.hst.issuergp.util.extensions.mapToIssuerGpError
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.google.gson.Gson


class LoaderGoogleSdk : AppCompatActivity() {

    companion object {
        fun start(
            from: Activity, params: String, methodCall: String, call: PluginCall, plugin: Plugin
        ) {
            val intent = Intent(from, LoaderGoogleSdk::class.java).apply {
                putExtra(Constants.METHOD, methodCall)
                putExtra(Constants.DATA, params)
            }
            plugin.startActivityForResult(call, intent, "retrieveProvisioningResult")
        }
    }

    private val methodCall by lazy { intent?.getStringExtra(Constants.METHOD) as String }
    private val payload by lazy { intent?.getStringExtra(Constants.DATA) as String }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT

        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            val statusBarHeight = insets.systemWindowInsetTop
            view.setPadding(0, statusBarHeight, 0, 0)
            insets.consumeSystemWindowInsets()
        }

        val container = FrameLayout(this)
        container.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT
        )

        val progressBar = ProgressBar(this)
        val progressBarSize = dpToPx()
        val progressBarParams = FrameLayout.LayoutParams(progressBarSize, progressBarSize)
        progressBarParams.gravity = Gravity.CENTER
        progressBar.layoutParams = progressBarParams
        progressBar.indeterminateDrawable.setColorFilter(
            Color.BLACK, android.graphics.PorterDuff.Mode.MULTIPLY
        )

        container.addView(progressBar)
        setContentView(container)
        handleApiCalls()
    }

    private fun dpToPx(): Int {
        val density = resources.displayMetrics.density
        return (30 * density).toInt()
    }

    private fun handleApiCalls() {
        when (methodCall) {
            Constants.EXEC_TOKENIZE -> handleTokenizeApi()
            Constants.EXEC_PROVISIONING -> handleProvisioningApi()
            Constants.EXEC_PROV_OF_ENC_CARD -> handleProvisioningOfEncryptedCardApi()
            else -> handleError(Exception("invalid request"))
        }
    }

    private fun handleTokenizeApi() {
        try {
            val tokenizeData = Gson().fromJson(payload, TokenizeDataPlugin::class.java)
            IssuerGP.tokenize(
                this, tokenizeData.tokenReferenceId, tokenizeData.provisionInfo
            ) { throwable ->
                this.handleError(throwable)
            }
        } catch (thr: Throwable) {
            this.handleError(thr)
        }
    }

    private fun handleProvisioningApi() {
        try {
            val provisioningObj = Gson().fromJson(payload, ProvisioningDataPlugin::class.java)
            IssuerGP.executeProvisioning(
                this,
                provisioningObj.instCode,
                provisioningObj.pushReceiptId,
                provisioningObj.provisionInfo
            ) { throwable ->
                this.handleError(throwable)
            }
        } catch (thr: Throwable) {
            this.handleError(thr)
        }
    }

    private fun handleProvisioningOfEncryptedCardApi() {
        try {
            val provisioningObj = Gson().fromJson(payload, ProvisioningEncDataPlugin::class.java)
            IssuerGP.executeProvisioningOfEncryptedCard(
                this,
                provisioningObj.instCode,
                provisioningObj.encryptedCard,
                provisioningObj.provisionInfo
            ) { throwable ->
                this.handleError(throwable)
            }
        } catch (thr: Throwable) {
            this.handleError(thr)
        }
    }

    private fun handleError(thr: Throwable) {
        val error = if (thr !is IssuerGPException) thr.mapToIssuerGpError() else thr
        val returnError = Gson().toJson(funcErrorCompiler(error))
        val resultIntent = Intent().apply {
            putExtra(Constants.DATA, returnError)
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    private fun funcErrorCompiler(thr: Throwable): DefaultResponse<DefaultErrorResponse> {
        return DefaultResponse<DefaultErrorResponse>(
            result = ResultStatus.FAILURE,
            data = DefaultErrorResponse(
                statusCode = if (thr is IssuerGPException)
                    thr.statusCode else IssuerGpStatusCode.GENERAL_ERROR,
                message = thr.message.toString()
            )
        )
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var provision = ProvisioningResult(wasSuccessful = false, tokenId = null)
        val resultStatus: ResultStatus = ResultStatus.SUCCESS

        if (requestCode == IssuerGP.REQUEST_PUSH_TOKENIZE ||
            requestCode == IssuerGP.REQUEST_PUSH_TOKENIZE
        ) {
            provision = IssuerGPUtil.retrieveProvisioningResult(resultCode, data)
        }
        val response: DefaultResponse<ProvisioningResult> = DefaultResponse(resultStatus, provision)

        val resultIntent = Intent().apply {
            putExtra(Constants.DATA, Gson().toJson(response))
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}