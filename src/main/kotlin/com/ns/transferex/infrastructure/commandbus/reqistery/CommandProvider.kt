package com.ns.transferex.infrastructure.commandbus.reqistery

import com.ns.transferex.application.commandbus.CommandHandler
import io.micronaut.context.ApplicationContext


/**
 * CommandProvider creates a handler with enabled micronaut injection.
 *
 * @param <H> type of handler
</H> */
internal class CommandProvider<H : CommandHandler<*, *>>(private val applicationContext: ApplicationContext, private val type: Class<H>) {

    fun get(): H {
        return applicationContext.getBean(type)
    }
}

