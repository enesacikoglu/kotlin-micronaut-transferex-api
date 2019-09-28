package com.ns.transferex.application.web

import com.ns.transferex.application.commands.CreateAccountCommand
import com.ns.transferex.application.queries.GetAccountByIdQuery
import com.ns.transferex.domain.models.GetAccountByIdResponse
import com.ns.transferex.infrastructure.commandbus.CommandBus
import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.Operation
import javax.validation.Valid

@Controller("/accounts")
open class AccountController(private val commandBus: CommandBus) {

    @Get("/{accountId}")
    @Operation(summary = "Get Accounts",
            tags = ["Accounts"],
            description = "This endpoint retrieves all accounts")
    open fun getAccountById(@PathVariable("accountId") accountId: Int): GetAccountByIdResponse? {
        return commandBus.executeQuery(GetAccountByIdQuery(accountId))
    }

    @Post
    @Operation(summary = "Create Account",
            tags = ["Account"],
            description = "This endpoint create an account")
    open fun createAccount(@Body @Valid createAccountCommand: CreateAccountCommand) {
        commandBus.executeCommand(createAccountCommand)
    }

}