package com.ns.transferex.domain.models

import java.math.BigDecimal

data class GetTransactionByIdResponse(val id: Int,
                                      val fromAccount: Int,
                                      val toAccount: Int,
                                      val amount: BigDecimal)