package com.ns.transferex.application.queries.transaction

import com.ns.transferex.application.service.TransactionService
import com.ns.transferex.domain.models.GetTransactionByIdResponse
import com.ns.transferex.infrastructure.commandbus.QueryHandler
import javax.inject.Singleton


@Singleton
class GetAllTransactionsQueryHandler(private val transactionService: TransactionService) : QueryHandler<List<GetTransactionByIdResponse>, GetAllTransactionsQuery> {
    override fun handle(query: GetAllTransactionsQuery): List<GetTransactionByIdResponse> {
        val transactions = transactionService.getAll()
        return transactions.map { GetTransactionByIdResponse(it.id, it.fromAccount, it.toAccount, it.amount) }
    }
}