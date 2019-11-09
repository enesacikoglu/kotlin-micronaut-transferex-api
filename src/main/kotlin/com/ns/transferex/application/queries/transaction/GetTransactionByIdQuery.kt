package com.ns.transferex.application.queries.transaction

import com.ns.transferex.application.commandbus.Query

data class GetTransactionByIdQuery(val transactionId: Int) : Query<GetTransactionByIdResponse?>
