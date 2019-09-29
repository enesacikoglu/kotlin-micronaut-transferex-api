package com.ns.transferex.application.web

import com.ns.transferex.application.commands.account.CreateAccountCommand
import com.ns.transferex.infrastructure.commandbus.CommandBus
import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.Operation
import javax.validation.Valid

@Controller("/transactions")
open class TransactionController(private val commandBus: CommandBus) {


    @Post
    @Operation(summary = "Create Account",
            tags = ["Account"],
            description = "This endpoint create an account")
    open fun createAccount(@Body @Valid createAccountCommand: CreateAccountCommand) {
        commandBus.executeCommand(createAccountCommand)
    }

}