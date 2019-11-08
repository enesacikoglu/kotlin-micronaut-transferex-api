package com.ns.transferex.infrastructure.commandbus.reqistery

import com.ns.transferex.application.commandbus.QueryHandler
import io.micronaut.context.ApplicationContext

internal class QueryProvider<H : QueryHandler<*, *>>(private val applicationContext: ApplicationContext, private val type: Class<H>) {

    fun get(): H {
        return applicationContext.getBean(type)
    }
}