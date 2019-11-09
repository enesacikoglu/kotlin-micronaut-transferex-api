package com.ns.transferex.application.commands.account

import com.ns.transferex.application.commandbus.Command
import java.math.BigDecimal

data class CreateAccountCommand(val owner: String, val balance: BigDecimal) : Command<Unit>
