package br.com.hst.capacitorissuersdk

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import br.com.hst.capacitorissuersdk.handlers.issuergp.*
import br.com.hst.capacitorissuersdk.model.issuergp.*
import br.com.hst.issuergp.core.IssuerGP
import br.com.hst.issuergp.core.IssuerGPUtil
import br.com.hst.issuergp.data.model.*
import com.getcapacitor.Bridge
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin
import com.google.gson.Gson
import androidx.activity.result.ActivityResult
import com.getcapacitor.annotation.ActivityCallback
import br.com.hst.capacitorissuersdk.utils.Constants

@CapacitorPlugin(name = "CapacitorIssuerGooglePay")
class CapacitorGooglePay : Plugin() {

    private var issuergp: IssuerGpRepository? = null

    private var callback: PluginCall? = null
    private var activity: AppCompatActivity? = null

    override fun load() {
        this.activity = bridge.activity
        this.activity?.let { actv ->
            issuergp = IssuerGpHandler(actv)
        }
    }

    @PluginMethod
    fun init(call: PluginCall) =
        issuergp?.init(call)

    @PluginMethod
    fun isAvailable(call: PluginCall) =
        issuergp?.isAvailable(call)

    @PluginMethod
    fun getTokens(call: PluginCall) =
        issuergp?.getTokens(call)

    @PluginMethod
    fun containsToken(call: PluginCall) =
        issuergp?.containsToken(call.data.toString(), call)

    @PluginMethod
    fun tokenize(call: PluginCall) =
        issuergp?.tokenize(call.data.toString(), call, this)

    @PluginMethod
    fun executeProvisioning(call: PluginCall) =
        issuergp?.executeProvisioning(call.data.toString(), call, this)

    @PluginMethod
    fun executeProvisioningOfEncryptedCard(call: PluginCall) =
        issuergp?.executeProvisioningOfEncryptedCard(call.data.toString(), call, this)

    @ActivityCallback
    fun retrieveProvisioningResult(call: PluginCall?, result: ActivityResult) {
        if (call == null) { return }

        result.data?.let { intentData ->
            val json = intentData.getStringExtra(Constants.DATA)
            call.resolve(JSObject(json))
        } ?: call.reject("result callback has no valid data")
    }
}
