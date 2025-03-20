package br.com.hst.capacitorissuersdk.model.issuergp

import br.com.hst.issuergp.data.model.ProvisionInfo

data class ProvisioningDataPlugin(
    val instCode: String,
    val pushReceiptId: String,
    val provisionInfo: ProvisionInfo,
)
