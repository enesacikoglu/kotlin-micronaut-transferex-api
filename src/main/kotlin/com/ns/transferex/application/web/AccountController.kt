package com.ns.transferex.application.web

import com.ns.transferex.application.commands.account.CreateAccountCommand
import com.ns.transferex.application.queries.account.GetAccountByIdQuery
import com.ns.transferex.application.queries.account.GetAllAccountsQuery
import com.ns.transferex.domain.models.GetAccountByIdResponse
import com.ns.transferex.infrastructure.commandbus.CommandBus
import io.micronaut.http.annotation.*

@Controller("/accounts")
open class AccountController(private val commandBus: CommandBus) {

    @Get
    open fun getAllAccounts(): List<GetAccountByIdResponse> {
        return commandBus.executeQuery(GetAllAccountsQuery())
    }

    @Get("/{accountId}")
    open fun getAccountById(@PathVariable("accountId") accountId: Int): GetAccountByIdResponse? {
        return commandBus.executeQuery(GetAccountByIdQuery(accountId))
    }

    @Post
    open fun createAccount(@Body createAccountCommand: CreateAccountCommand) {
        commandBus.executeCommand(createAccountCommand)
    }

}