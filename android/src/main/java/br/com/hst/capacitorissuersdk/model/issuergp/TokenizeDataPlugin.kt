package br.com.hst.capacitorissuersdk.model.issuergp

import br.com.hst.issuergp.data.model.ProvisionInfo

data class TokenizeDataPlugin(
    val tokenReferenceId: String,
    val provisionInfo: ProvisionInfo,
)
