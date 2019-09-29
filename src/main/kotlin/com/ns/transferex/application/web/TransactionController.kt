package com.ns.transferex.application.web

import com.ns.transferex.application.queries.transaction.GetAllTransactionsQuery
import com.ns.transferex.application.queries.transaction.GetTransactionByIdQuery
import com.ns.transferex.domain.models.GetTransactionByIdResponse
import com.ns.transferex.infrastructure.commandbus.CommandBus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable

@Controller("/transactions")
open class TransactionController(private val commandBus: CommandBus) {

    @Get
    open fun getAllTransactions(): List<GetTransactionByIdResponse> {
        return commandBus.executeQuery(GetAllTransactionsQuery())
    }

    @Get("/{transactionId}")
    open fun getTransactionById(@PathVariable("transactionId") transactionId: Int): GetTransactionByIdResponse? {
        return commandBus.executeQuery(GetTransactionByIdQuery(transactionId))
    }
}