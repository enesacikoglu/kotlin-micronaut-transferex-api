package com.ns.transferex.application.commands.transaction

import com.ns.transferex.application.service.TransactionService
import com.ns.transferex.domain.Transaction
import com.ns.transferex.infrastructure.commandbus.CommandHandler
import javax.inject.Singleton

@Singleton
class CreateTransactionCommandHandler(val transactionService: TransactionService) : CommandHandler<Unit, CreateTransactionCommand> {

    override fun handle(command: CreateTransactionCommand) {
        transactionService.save(Transaction.new(command.fromAccount, command.toAccount, command.amount))
    }
}