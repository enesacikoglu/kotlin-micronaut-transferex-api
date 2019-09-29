package com.ns.transferex.application.queries.transaction

import com.ns.transferex.application.queries.account.GetTransactionByIdQuery
import com.ns.transferex.application.service.TransactionService
import com.ns.transferex.domain.models.GetTransactionByIdResponse
import com.ns.transferex.infrastructure.commandbus.QueryHandler
import javax.inject.Singleton

@Singleton
class GetTransactionByIdQueryHandler(private val transactionService: TransactionService) : QueryHandler<GetTransactionByIdResponse?, GetTransactionByIdQuery> {
    override fun handle(query: GetTransactionByIdQuery): GetTransactionByIdResponse? {
        with(query) {
            val transaction = transactionService.getTransactionById(transactionId)
            return GetTransactionByIdResponse(transactionId, transaction.fromAccount, transaction.toAccount, transaction.amount)
        }
    }
}