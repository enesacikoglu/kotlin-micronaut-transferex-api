package com.ns.transferex.application.commands.transfer

import com.ns.transferex.infrastructure.commandbus.Command
import java.math.BigDecimal
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotNull

data class TransferOneAccountToAnotherCommand(@NotNull(message = "sender.account.can.not.be.empty")
                                val fromAccount: Int,
                                @NotNull(message = "receiver.account.can.not.be.empty")
                                val toAccount: Int,
                                @DecimalMin(value = "0.0001", inclusive = false, message = "transfer.amount.can.not.be.negative")
                                val amount: BigDecimal) : Command<Unit>