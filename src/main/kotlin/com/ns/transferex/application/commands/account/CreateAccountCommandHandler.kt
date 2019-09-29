package com.ns.transferex.application.commands.account

import com.ns.transferex.application.service.AccountService
import com.ns.transferex.domain.Account
import com.ns.transferex.infrastructure.commandbus.CommandHandler
import javax.inject.Singleton

@Singleton
class CreateAccountCommandHandler(private val accountService: AccountService) :
        CommandHandler<Unit, CreateAccountCommand> {

    override fun handle(command: CreateAccountCommand) {
        accountService.save(Account.new(command.owner, command.balance))
    }
}
