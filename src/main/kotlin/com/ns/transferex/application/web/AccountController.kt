package com.ns.transferex.application.web

import com.ns.transferex.domain.models.GetAccountByIdResponse
import com.trendyol.productcontenteditorapi.application.commands.CreateAccountCommand
import com.trendyol.productcontenteditorapi.application.queries.GetAccountByIdQuery
import com.trendyol.productcontenteditorapi.infra.commandbus.CommandBus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.Operation
import kotlinx.coroutines.Deferred
import javax.validation.Valid

@Controller("/accounts")
open class AccountController(private val commandBus: CommandBus) {

    @Get("/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Accounts",
            tags = ["Accounts"],
            description = "This endpoint retrieves all accounts")
    open suspend fun getAccountById(@PathVariable("accountId") accountId:Int): Deferred<GetAccountByIdResponse?> {
        return commandBus.executeQueryAsync(GetAccountByIdQuery(accountId))
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create Account",
            tags = ["Account"],
            description = "This endpoint create an account")
    open suspend fun createAccount(@Body @Valid createAccountCommand: CreateAccountCommand): Deferred<Unit> {
        return commandBus.executeCommandAsync(createAccountCommand)
    }

}