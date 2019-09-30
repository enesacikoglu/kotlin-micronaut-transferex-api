package com.ns.transferex.domain.business.validator

import com.ns.transferex.application.commands.transfer.TransferOneAccountToAnotherCommand
import com.ns.transferex.application.exceptions.BusinessException
import java.math.BigDecimal
import javax.inject.Singleton

@Singleton
class TransferOneAccountToAnotherCommandValidator {

    fun validate(command: TransferOneAccountToAnotherCommand) {
        if (command.amount < BigDecimal.ZERO)
            throw BusinessException("Transfer amount can not be negative")
    }
}