package com.ns.transferex.application.queries.transaction

import com.ns.transferex.domain.models.GetTransactionByIdResponse
import com.ns.transferex.infrastructure.commandbus.Query

class GetAllTransactionsQuery : Query<List<GetTransactionByIdResponse>>