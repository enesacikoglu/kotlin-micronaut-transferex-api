package com.ns.transferex.application.commands.transaction

import com.ns.transferex.application.persistence.TransactionRepository
import com.ns.transferex.domain.Transaction
import com.ns.transferex.application.commandbus.CommandHandler
import javax.inject.Singleton

@Singleton
class CreateTransactionCommandHandler(private val transactionRepository: TransactionRepository)
    : CommandHandler<Unit, CreateTransactionCommand> {

    override fun handle(command: CreateTransactionCommand) {
        transactionRepository.save(Transaction.new(command.fromAccount, command.toAccount, command.amount))
    }
}