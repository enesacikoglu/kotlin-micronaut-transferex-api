package com.ns.transferex.infrastructure.commandbus.reqistery

import com.ns.transferex.infrastructure.commandbus.*
import javax.inject.Singleton


/**
 * Micronaut backed Command-Bus.
 */
@Singleton
open class MicronautCommandBus(private val registry: Registry) : CommandBus {
    /**
     * Creates a new instance with the given registry.
     *
     * @param registry registry
     */
    override fun <R, Q : Query<R>> executeQuery(query: Q): R {
        val commandHandler = registry.getQuery(query.javaClass) as QueryHandler<R, Q>
        return commandHandler.handle(query)
    }

    override fun <TResponse, TCommand : Command<TResponse>> executeCommand(command: TCommand): TResponse {
        val commandHandler = registry.getCmd(command.javaClass) as CommandHandler<TResponse, TCommand>
        return commandHandler.handle(command)
    }
}
