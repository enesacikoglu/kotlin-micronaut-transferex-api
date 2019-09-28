package com.trendyol.productcontenteditorapi.application.commands

import com.trendyol.productcontenteditorapi.infra.commandbus.Command
import java.math.BigDecimal
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotBlank

data class CreateAccountCommand(@NotBlank(message = "owner.can.not.be.empty")
                                val owner: String,
                                @DecimalMin(value = "0.00", inclusive = false, message = "balance.can.not.be.negative")
                                val balance: BigDecimal) : Command<Unit>
