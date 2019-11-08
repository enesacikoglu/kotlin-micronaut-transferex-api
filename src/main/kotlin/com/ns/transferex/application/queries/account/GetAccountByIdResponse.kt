package com.ns.transferex.application.queries.account

import java.math.BigDecimal

data class GetAccountByIdResponse(val id: Int,
                                  val owner: String,
                                  val balance: BigDecimal)