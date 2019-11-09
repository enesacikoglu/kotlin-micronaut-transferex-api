package com.ns.transferex.application.commandbus

interface CommandHandler<TResponse, TCommand : Command<TResponse>> {
    fun handle(command: TCommand): TResponse
}

