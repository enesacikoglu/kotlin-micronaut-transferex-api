package com.ns.transferex.application.commands

import com.ns.transferex.infrastructure.commandbus.Command
import java.math.BigDecimal
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotBlank

data class CreateAccountCommand(@NotBlank(message = "owner.can.not.be.empty")
                                val owner: String,
                                @DecimalMin(value = "0.00", inclusive = false, message = "balance.can.not.be.negative")
                                val balance: BigDecimal) : Command<Unit>
