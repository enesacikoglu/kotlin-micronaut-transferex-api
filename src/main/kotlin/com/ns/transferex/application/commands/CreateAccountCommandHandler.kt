package com.trendyol.productcontenteditorapi.application.commands

import com.ns.transferex.application.service.AccountService
import com.ns.transferex.domain.Account
import com.trendyol.productcontenteditorapi.infra.commandbus.CommandHandler
import javax.inject.Singleton

@Singleton
class CreateAccountCommandHandler(private val accountService: AccountService) :
        CommandHandler<Unit, CreateAccountCommand> {

    override suspend fun handleAsync(command: CreateAccountCommand) {
        accountService.save(Account.new(command.owner, command.balance))
    }
}
