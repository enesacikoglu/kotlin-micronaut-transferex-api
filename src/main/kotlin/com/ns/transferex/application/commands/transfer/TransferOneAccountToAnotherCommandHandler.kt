package com.ns.transferex.application.commands.transfer

import com.ns.transferex.application.service.TransferService
import com.ns.transferex.domain.Transaction
import com.ns.transferex.domain.business.validator.TransferOneAccountToAnotherCommandValidator
import com.ns.transferex.infrastructure.commandbus.CommandHandler
import javax.inject.Singleton

@Singleton
class TransferOneAccountToAnotherCommandHandler(private val transferService: TransferService,
                                                private val transferOneAccountToAnotherCommandValidator: TransferOneAccountToAnotherCommandValidator) : CommandHandler<Unit, TransferOneAccountToAnotherCommand> {

    override fun handle(command: TransferOneAccountToAnotherCommand) {
        transferOneAccountToAnotherCommandValidator.validate(command)
        transferService.applyTransfer(Transaction.new(command.fromAccount, command.toAccount, command.amount))
    }
}