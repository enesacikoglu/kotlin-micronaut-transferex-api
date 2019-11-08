package com.ns.transferex.application.commands.transfer

import com.ns.transferex.application.exceptions.DomainNotFoundException
import com.ns.transferex.application.persistence.AccountRepository
import com.ns.transferex.application.persistence.TransactionRepository
import com.ns.transferex.application.commandbus.CommandHandler
import javax.inject.Singleton

@Singleton
class TransferOneAccountToAnotherCommandHandler(private val accountRepository: AccountRepository,
                                                private val transactionRepository: TransactionRepository)
    : CommandHandler<Unit, TransferOneAccountToAnotherCommand> {

    override fun handle(command: TransferOneAccountToAnotherCommand) {
        val fromAccount = accountRepository.findById(command.fromAccount)
                .orElseThrow { throw DomainNotFoundException("Account not found") }
        val toAccount = accountRepository.findById(command.toAccount)
                .orElseThrow { throw DomainNotFoundException("Account not found") }
        
        var transaction = fromAccount.transferTo(toAccount,command.amount)
        accountRepository.save(fromAccount)
        accountRepository.save(toAccount)
        transactionRepository.save(transaction)
    }
}