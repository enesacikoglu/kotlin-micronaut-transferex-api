package com.trendyol.productcontenteditorapi.infra.commandbus

interface CommandHandler<TResponse, TCommand : Command<TResponse>> {
    suspend fun handleAsync(command: TCommand): TResponse
}

