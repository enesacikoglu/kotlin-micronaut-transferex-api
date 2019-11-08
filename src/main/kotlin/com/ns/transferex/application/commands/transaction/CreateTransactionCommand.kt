package com.ns.transferex.application.commands.transaction

import com.ns.transferex.application.commandbus.Command
import java.math.BigDecimal

data class CreateTransactionCommand(var fromAccount: Int, var toAccount: Int, var amount: BigDecimal) : Command<Unit>