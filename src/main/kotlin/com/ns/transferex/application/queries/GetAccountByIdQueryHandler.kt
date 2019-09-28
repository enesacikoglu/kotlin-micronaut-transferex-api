package com.ns.transferex.application.queries

import com.ns.transferex.application.service.AccountService
import com.ns.transferex.domain.models.GetAccountByIdResponse
import com.ns.transferex.infrastructure.commandbus.QueryHandler
import javax.inject.Singleton

@Singleton
class GetAccountByIdQueryHandler(private val accountService: AccountService) : QueryHandler<GetAccountByIdResponse?, GetAccountByIdQuery> {
    override fun handle(query: GetAccountByIdQuery): GetAccountByIdResponse? {
        with(query) {
            val account = accountService.getAccountById(accountId)
            return GetAccountByIdResponse(accountId, account.owner, account.balance)
        }
    }
}