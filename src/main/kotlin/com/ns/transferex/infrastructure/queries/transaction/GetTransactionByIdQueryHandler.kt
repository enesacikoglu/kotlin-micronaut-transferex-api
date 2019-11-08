package com.ns.transferex.infrastructure.queries.transaction

import com.ns.transferex.application.exceptions.DomainNotFoundException
import com.ns.transferex.application.queries.transaction.GetTransactionByIdQuery
import com.ns.transferex.application.queries.transaction.GetTransactionByIdResponse
import com.ns.transferex.domain.Transaction
import com.ns.transferex.application.commandbus.QueryHandler
import java.util.*
import javax.inject.Singleton
import javax.persistence.EntityManager

@Singleton
class GetTransactionByIdQueryHandler(private val entityManager: EntityManager)
    : QueryHandler<GetTransactionByIdResponse?, GetTransactionByIdQuery> {
    override fun handle(query: GetTransactionByIdQuery): GetTransactionByIdResponse? {
        with(query) {
            val transaction =Optional.ofNullable(entityManager
                    .createQuery("Select t from Transaction t where t.Id = :id", Transaction::class.java)
                    .setParameter("id",query.transactionId)
                    .singleResult)
                    .orElseThrow { throw DomainNotFoundException("Transaction not found!") }
            return GetTransactionByIdResponse(transactionId, transaction.fromAccount, transaction.toAccount, transaction.amount)
        }
    }
}