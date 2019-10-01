package com.ns.transferex.domain.business.validator

import com.ns.transferex.application.commands.transfer.TransferOneAccountToAnotherCommand
import com.ns.transferex.application.exceptions.BusinessException
import java.math.BigDecimal
import javax.inject.Singleton

@Singleton
open class TransferOneAccountToAnotherCommandValidator {

    open fun validate(command: TransferOneAccountToAnotherCommand) {
        if (command.amount <= BigDecimal.ZERO)
            throw BusinessException("Transfer amount must be positive")
    }
}