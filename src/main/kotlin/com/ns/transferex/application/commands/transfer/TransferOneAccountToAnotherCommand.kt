package com.ns.transferex.application.commands.transfer

import com.ns.transferex.application.commandbus.Command
import java.math.BigDecimal

data class TransferOneAccountToAnotherCommand(val fromAccount: Int, val toAccount: Int, val amount: BigDecimal) : Command<Unit>