package br.com.hst.capacitorissuersdk.model.issuergp

import br.com.hst.issuergp.data.model.CardNetwork
import br.com.hst.issuergp.data.model.TokenServiceProvider

data class ContainsTokenPlugin(
    val panLast4: String,
    val cardNetwork: CardNetwork,
    val tokenServiceProvider: TokenServiceProvider,
)
