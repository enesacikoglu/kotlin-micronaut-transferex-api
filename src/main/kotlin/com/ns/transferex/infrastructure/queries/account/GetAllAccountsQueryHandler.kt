package com.ns.transferex.infrastructure.queries.account

import com.ns.transferex.application.queries.account.GetAllAccountsQuery
import com.ns.transferex.application.queries.account.GetAccountByIdResponse
import com.ns.transferex.domain.Account
import com.ns.transferex.application.commandbus.QueryHandler
import javax.inject.Singleton
import javax.persistence.EntityManager


@Singleton
class GetAllAccountsQueryHandler(private val entityManager: EntityManager) 
        : QueryHandler<List<GetAccountByIdResponse>, GetAllAccountsQuery> {
    override fun handle(query: GetAllAccountsQuery): List<GetAccountByIdResponse> {
        val accounts = entityManager
                .createQuery("Select a from Account a", Account::class.java)
                .resultList
        return accounts.map { GetAccountByIdResponse(it.id, it.owner, it.balance) }
    }
}