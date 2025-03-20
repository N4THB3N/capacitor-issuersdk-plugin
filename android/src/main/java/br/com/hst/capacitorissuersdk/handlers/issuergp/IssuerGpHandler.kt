package br.com.hst.capacitorissuersdk.handlers.issuergp

import CoreHandler
import androidx.appcompat.app.AppCompatActivity
import br.com.hst.capacitorissuersdk.model.issuergp.*
import br.com.hst.capacitorissuersdk.utils.Constants
import br.com.hst.issuergp.core.IssuerGP
import br.com.hst.issuergp.data.model.*
import br.com.hst.issuergp.exception.IssuerGPException
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.google.gson.Gson

class IssuerGpHandler(
    private val activity: AppCompatActivity
) : IssuerGpRepository {

    override fun init(call: PluginCall) = try {
        IssuerGP.init(activity.applicationContext)
        CoreHandler.handleSuccess(
            Gson().toJson(
                DefaultResponse(
                    result = ResultStatus.SUCCESS,
                    data = IssuerGpInitializer()
                )
            ), call
        )
    } catch (thr: Throwable) {
        CoreHandler.handleError(call, funcErrorCompiler(thr))
    }

    override fun isAvailable(call: PluginCall) =
        CoreHandler.handleSuccess(
            Gson().toJson(
                DefaultResponse(
                    result = ResultStatus.SUCCESS,
                    data = IsAvailablePlugin(IssuerGP.isAvailable())
                )
            ), call
        )

    override fun getTokens(call: PluginCall) = try {
        IssuerGP.getTokens(activity.applicationContext, object : Callback<List<GooglePayToken>> {
            override fun onError(throwable: Throwable) {
                CoreHandler.handleError(call, funcErrorCompiler(throwable))
            }

            override fun onFinish(data: List<GooglePayToken>) =
                CoreHandler.handleSuccess(
                    Gson().toJson(
                        DefaultResponse(
                            result = ResultStatus.SUCCESS,
                            data = TokensPlugin(data)
                        )
                    ),
                    call
                )
        })
    } catch (thr: Throwable) {
        CoreHandler.handleError(call, funcErrorCompiler(thr))
    }

    override fun containsToken(params: String, call: PluginCall) = try {
        val cTokens: ContainsTokenPlugin = Gson().fromJson(params, ContainsTokenPlugin::class.java)

        IssuerGP.containsToken(
            activity.applicationContext,
            cTokens.panLast4,
            cTokens.cardNetwork,
            cTokens.tokenServiceProvider,
            object : Callback<Boolean> {
                override fun onError(throwable: Throwable) =
                    CoreHandler.handleError(call, funcErrorCompiler(throwable))

                override fun onFinish(data: Boolean) =
                    CoreHandler.handleSuccess(
                        Gson().toJson(
                            DefaultResponse(
                                result = ResultStatus.SUCCESS,
                                data = CTokensPlugin(containsToken = data)
                            )
                        ), call
                    )
            }
        )
    } catch (thr: Throwable) {
        CoreHandler.handleError(call, funcErrorCompiler(thr))
    }

    override fun tokenize(params: String, call: PluginCall, plugin: Plugin) = try {
        LoaderGoogleSdk
            .start(activity, params, Constants.EXEC_TOKENIZE, call, plugin)
    } catch (thr: Throwable) {
        CoreHandler.handleError(call, funcErrorCompiler(thr))
    }

    override fun executeProvisioning(params: String, call: PluginCall, plugin: Plugin) = try {
        LoaderGoogleSdk
            .start(activity, params, Constants.EXEC_PROVISIONING, call, plugin)
    } catch (thr: Throwable) {
        CoreHandler.handleError(call, funcErrorCompiler(thr))
    }

    override fun executeProvisioningOfEncryptedCard(
        params: String,
        call: PluginCall,
        plugin: Plugin
    ) = try {
        LoaderGoogleSdk
            .start(activity, params, Constants.EXEC_PROV_OF_ENC_CARD, call, plugin)
    } catch (thr: Throwable) {
        CoreHandler.handleError(call, funcErrorCompiler(thr))
    }

    private fun funcErrorCompiler(thr: Throwable): String =
        Gson().toJson(
            DefaultResponse(
                result = ResultStatus.FAILURE,
                data = DefaultErrorResponse(
                    statusCode = if (thr is IssuerGPException)
                        thr.statusCode else IssuerGpStatusCode.GENERAL_ERROR,
                    message = thr.message.toString()
                )
            )
        )
}
