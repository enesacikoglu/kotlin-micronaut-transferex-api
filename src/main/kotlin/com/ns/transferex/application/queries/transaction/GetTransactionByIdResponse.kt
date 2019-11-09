package com.ns.transferex.application.queries.transaction

import java.math.BigDecimal

data class GetTransactionByIdResponse(val id: Int,
                                      val fromAccount: Int,
                                      val toAccount: Int,
                                      val amount: BigDecimal)