package com.ns.transferex.application.queries

import com.ns.transferex.application.service.AccountService
import com.ns.transferex.domain.models.GetAccountByIdResponse
import com.trendyol.productcontenteditorapi.application.queries.GetAccountByIdQuery
import com.trendyol.productcontenteditorapi.infra.commandbus.QueryHandler
import javax.inject.Singleton

@Singleton
class GetAccountByIdQueryHandler(private val accountService: AccountService) : QueryHandler<GetAccountByIdResponse?, GetAccountByIdQuery> {
    override suspend fun handleAsync(query: GetAccountByIdQuery): GetAccountByIdResponse? {
        with(query) {
            val account = accountService.getAccountById(accountId)
            return GetAccountByIdResponse(accountId, account.owner, account.balance)
        }
    }
}