package com.trendyol.productcontenteditorapi.infra.commandbus.spring

import com.trendyol.productcontenteditorapi.infra.commandbus.CommandHandler
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

