package br.com.hst.capacitorissuersdk.handlers.issuergp

import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall

interface IssuerGpRepository {

    fun init(call: PluginCall)
    fun isAvailable(call: PluginCall)
    fun getTokens(call: PluginCall)
    fun containsToken(params: String, call: PluginCall)
    fun tokenize(params: String, call: PluginCall, plugin: Plugin)
    fun executeProvisioning(params: String, call: PluginCall, plugin: Plugin)
    fun executeProvisioningOfEncryptedCard(
        params: String,
        call: PluginCall,
        plugin: Plugin
    )
}