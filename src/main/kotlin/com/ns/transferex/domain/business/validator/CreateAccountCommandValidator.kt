package com.ns.transferex.domain.business.validator

import com.ns.transferex.application.commands.account.CreateAccountCommand
import com.ns.transferex.application.exceptions.BusinessException

import java.math.BigDecimal
import javax.inject.Singleton

@Singleton
open class CreateAccountCommandValidator {

    open fun validate(command: CreateAccountCommand) {
        if (command.balance <= BigDecimal.ZERO)
            throw BusinessException("Balance must be positive")
    }
}