package com.ns.transferex.application.queries.account

import com.ns.transferex.domain.models.GetTransactionByIdResponse
import com.ns.transferex.infrastructure.commandbus.Query

data class GetTransactionByIdQuery(val transactionId: Int) : Query<GetTransactionByIdResponse?>