package com.trendyol.productcontenteditorapi.infra.commandbus.spring

import com.trendyol.productcontenteditorapi.infra.commandbus.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.springframework.stereotype.Service

/**
 * Micronaut backed Command-Bus.
 */
@Service
class MicronautCommandBus(private val registry: Registry) : CommandBus {
    /**
     * Creates a new instance with the given registry.
     *
     * @param registry registry
     */
    override suspend fun <R, Q : Query<R>> executeQueryAsync(query: Q): Deferred<R> {
        val commandHandler = registry.getQuery(query.javaClass) as QueryHandler<R, Q>
        return GlobalScope.async { commandHandler.handleAsync(query) }
    }

    override suspend fun <TResponse, TCommand : Command<TResponse>> executeCommandAsync(command: TCommand): Deferred<TResponse> {
        val commandHandler = registry.getCmd(command.javaClass) as CommandHandler<TResponse, TCommand>
        return GlobalScope.async { commandHandler.handleAsync(command) }
    }
}
