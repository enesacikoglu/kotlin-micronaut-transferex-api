package com.ns.transferex.infrastructure.queries.transaction

import com.ns.transferex.application.queries.transaction.GetAllTransactionsQuery
import com.ns.transferex.application.queries.transaction.GetTransactionByIdResponse
import com.ns.transferex.domain.Transaction
import com.ns.transferex.application.commandbus.QueryHandler
import javax.inject.Singleton
import javax.persistence.EntityManager


@Singleton
class GetAllTransactionsQueryHandler(private val entityManager: EntityManager)
    : QueryHandler<List<GetTransactionByIdResponse>, GetAllTransactionsQuery> {
    override fun handle(query: GetAllTransactionsQuery): List<GetTransactionByIdResponse> {
        val transactions = entityManager
                .createQuery("Select t from Transaction t", Transaction::class.java)
                .resultList
        return transactions.map { GetTransactionByIdResponse(it.id, it.fromAccount, it.toAccount, it.amount) }
    }
}