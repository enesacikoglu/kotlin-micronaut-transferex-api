package com.ns.transferex.application.queries.account

import com.ns.transferex.application.service.AccountService
import com.ns.transferex.domain.models.GetAccountByIdResponse
import com.ns.transferex.infrastructure.commandbus.QueryHandler
import javax.inject.Singleton


@Singleton
class GetAllAccountsQueryHandler(private val accountService: AccountService) : QueryHandler<List<GetAccountByIdResponse>, GetAllAccountsQuery> {
    override fun handle(query: GetAllAccountsQuery): List<GetAccountByIdResponse> {
        val accounts = accountService.getAll()
        return accounts.map { GetAccountByIdResponse(it.id, it.owner, it.balance) }
    }
}