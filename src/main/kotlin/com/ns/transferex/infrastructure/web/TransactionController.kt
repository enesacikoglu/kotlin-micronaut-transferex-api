package com.ns.transferex.infrastructure.web

import com.ns.transferex.application.commands.transaction.CreateTransactionCommand
import com.ns.transferex.application.queries.transaction.GetAllTransactionsQuery
import com.ns.transferex.application.queries.transaction.GetTransactionByIdQuery
import com.ns.transferex.application.queries.transaction.GetTransactionByIdResponse
import com.ns.transferex.application.commandbus.CommandBus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post

@Controller("/transactions")
open class TransactionController(private val commandBus: CommandBus) {

    @Post
    open fun createTransaction(createTransactionCommand: CreateTransactionCommand) {
        commandBus.executeCommand(createTransactionCommand)
    }

    @Get
    open fun getAllTransactions(): List<GetTransactionByIdResponse> {
        return commandBus.executeQuery(GetAllTransactionsQuery())
    }

    @Get("/{transactionId}")
    open fun getTransactionById(@PathVariable("transactionId") transactionId: Int): GetTransactionByIdResponse? {
        return commandBus.executeQuery(GetTransactionByIdQuery(transactionId))
    }
}