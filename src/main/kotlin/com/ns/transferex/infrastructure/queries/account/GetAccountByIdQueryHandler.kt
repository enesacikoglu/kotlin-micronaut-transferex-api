package com.ns.transferex.infrastructure.queries.account

import com.ns.transferex.application.exceptions.DomainNotFoundException
import com.ns.transferex.application.queries.account.GetAccountByIdQuery
import com.ns.transferex.application.queries.account.GetAccountByIdResponse
import com.ns.transferex.domain.Account
import com.ns.transferex.application.commandbus.QueryHandler
import java.util.*
import javax.inject.Singleton
import javax.persistence.EntityManager

@Singleton
    class GetAccountByIdQueryHandler(private val entityManager: EntityManager)
    : QueryHandler<GetAccountByIdResponse?, GetAccountByIdQuery> {
    override fun handle(query: GetAccountByIdQuery): GetAccountByIdResponse? {
        with(query) {
            val account =  Optional.ofNullable(entityManager
                    .createQuery("Select a from Account a where a.Id = :id", Account::class.java)
                    .setParameter("id",query.accountId)
                    .singleResult)
                    .orElseThrow { throw DomainNotFoundException("account not found!") }
            return GetAccountByIdResponse(accountId, account.owner, account.balance)
        }
    }
}   