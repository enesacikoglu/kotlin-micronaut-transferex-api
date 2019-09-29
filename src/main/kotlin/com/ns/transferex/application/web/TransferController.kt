package com.ns.transferex.application.web

import com.ns.transferex.application.commands.transfer.TransferOneAccountToAnotherCommand
import com.ns.transferex.infrastructure.commandbus.CommandBus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import javax.validation.Valid

@Controller("/transfers")
open class TransferController(private val commandBus: CommandBus) {

    @Post
    open fun transfer(@Body @Valid transferOneAccountToAnotherCommand: TransferOneAccountToAnotherCommand) {
        commandBus.executeCommand(transferOneAccountToAnotherCommand)
    }

}