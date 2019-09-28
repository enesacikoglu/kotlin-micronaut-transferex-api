package com.trendyol.productcontenteditorapi.infra.commandbus.spring

import com.trendyol.productcontenteditorapi.infra.commandbus.QueryHandler
import io.micronaut.context.ApplicationContext

internal class QueryProvider<H : QueryHandler<*, *>>(private val applicationContext: ApplicationContext, private val type: Class<H>) {

    fun get(): H {
        return applicationContext.getBean(type)
    }
}