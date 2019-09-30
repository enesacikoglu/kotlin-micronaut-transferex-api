package com.ns.transferex.application.commands.account

import com.ns.transferex.application.service.AccountService
import com.ns.transferex.domain.Account
import com.ns.transferex.domain.business.validator.CreateAccountCommandValidator
import com.ns.transferex.infrastructure.commandbus.CommandHandler
import javax.inject.Singleton

@Singleton
class CreateAccountCommandHandler(private val accountService: AccountService,
                                  private val createAccountCommandValidator: CreateAccountCommandValidator) :
        CommandHandler<Unit, CreateAccountCommand> {

    override fun handle(command: CreateAccountCommand) {
        createAccountCommandValidator.validate(command)
        accountService.save(Account.new(command.owner, command.balance))
    }
}
