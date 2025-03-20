package br.com.hst.capacitorissuersdk.model.issuergp

import br.com.hst.issuergp.data.model.ProvisionInfo

data class ProvisioningEncDataPlugin(
    val instCode: String,
    val encryptedCard: String,
    val provisionInfo: ProvisionInfo,
)
