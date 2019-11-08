package com.ns.transferex.infrastructure.web

import com.ns.transferex.application.commands.transfer.TransferOneAccountToAnotherCommand
import com.ns.transferex.application.commandbus.CommandBus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/transfers")
open class TransferController(private val commandBus: CommandBus) {

    @Post
    open fun transfer(@Body transferOneAccountToAnotherCommand: TransferOneAccountToAnotherCommand) {
        commandBus.executeCommand(transferOneAccountToAnotherCommand)
    }

}