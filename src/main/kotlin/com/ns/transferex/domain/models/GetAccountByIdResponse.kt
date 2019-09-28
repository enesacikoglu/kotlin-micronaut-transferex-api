package com.ns.transferex.domain.models

import java.math.BigDecimal

data class GetAccountByIdResponse(val id: Int,
                                  val owner: String,
                                  val balance: BigDecimal)