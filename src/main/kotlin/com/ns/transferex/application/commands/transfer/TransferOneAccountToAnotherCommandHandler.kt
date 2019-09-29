package com.ns.transferex.application.commands.transfer

import com.ns.transferex.application.service.TransferService
import com.ns.transferex.domain.Transaction
import com.ns.transferex.infrastructure.commandbus.CommandHandler
import javax.inject.Singleton

@Singleton
class TransferOneAccountToAnotherCommandHandler(private val transferService: TransferService) : CommandHandler<Unit, TransferOneAccountToAnotherCommand> {

    override fun handle(command: TransferOneAccountToAnotherCommand) {
        transferService.applyTransfer(Transaction.new(command.fromAccount, command.toAccount, command.amount))
    }
}