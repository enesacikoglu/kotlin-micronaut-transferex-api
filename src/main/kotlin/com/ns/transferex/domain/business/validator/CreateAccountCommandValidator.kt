package com.ns.transferex.domain.business.validator

import com.ns.transferex.application.commands.account.CreateAccountCommand
import com.ns.transferex.application.exceptions.BusinessException

import java.math.BigDecimal
import javax.inject.Singleton

@Singleton
class CreateAccountCommandValidator {

    fun validate(command: CreateAccountCommand) {
        if (command.balance < BigDecimal.ZERO)
            throw BusinessException("Balance can not be negative")
    }
}