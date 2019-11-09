package com.ns.transferex.application.commands.account

import com.ns.transferex.application.persistence.AccountRepository
import com.ns.transferex.domain.Account
import com.ns.transferex.application.commandbus.CommandHandler
import javax.inject.Singleton

@Singleton
class CreateAccountCommandHandler(private val accountRepository: AccountRepository)
    : CommandHandler<Unit, CreateAccountCommand> {

    override fun handle(command: CreateAccountCommand) {
        accountRepository.save(Account.new(command.owner, command.balance))
    }
}
