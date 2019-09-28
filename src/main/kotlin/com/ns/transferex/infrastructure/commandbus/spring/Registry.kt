package com.trendyol.productcontenteditorapi.infra.commandbus.spring

import com.trendyol.productcontenteditorapi.infra.commandbus.Command
import com.trendyol.productcontenteditorapi.infra.commandbus.CommandHandler
import com.trendyol.productcontenteditorapi.infra.commandbus.Query
import com.trendyol.productcontenteditorapi.infra.commandbus.QueryHandler
import io.micronaut.context.ApplicationContext
import io.micronaut.inject.BeanDefinition
import org.springframework.core.GenericTypeResolver
import java.util.*
import javax.inject.Singleton


/**
 * Registry holds the mapping between a command and its handler. The registry should always be injected, by the micronaut
 * framework.
 */
@Singleton
class Registry(applicationContext: ApplicationContext) {

    private val commandProviderMap = HashMap<Class<out Command<*>>, CommandProvider<CommandHandler<*, *>>>()
    private val queryProviderMap = HashMap<Class<out Query<*>>, QueryProvider<QueryHandler<*, *>>>()


    init {
        val commandHandlers = applicationContext.getBeanDefinitions(CommandHandler::class.java)
        commandHandlers.forEach { command -> registerCommand(applicationContext, command) }

        val queryHandlers = applicationContext.getBeanDefinitions(QueryHandler::class.java)
        queryHandlers.forEach { query -> registerQuery(applicationContext, query) }
    }

    private fun registerCommand(applicationContext: ApplicationContext, bean: BeanDefinition<CommandHandler<*, *>>) {
        val handlerClass = bean.beanType
        val generics = GenericTypeResolver.resolveTypeArguments(handlerClass, CommandHandler::class.java)
        val commandType = generics!![1] as Class<Command<*>>
        commandProviderMap[commandType] = CommandProvider(applicationContext, handlerClass)
    }


    private fun registerQuery(applicationContext: ApplicationContext, bean: BeanDefinition<QueryHandler<*, *>>) {
        val handlerClass = bean.beanType
        val generics = GenericTypeResolver.resolveTypeArguments(handlerClass, QueryHandler::class.java)
        val queryType = generics!![1] as Class<Query<*>>
        queryProviderMap[queryType] = QueryProvider(applicationContext, handlerClass)
    }

    internal fun <R, C : Command<R>> getCmd(commandClass: Class<C>): CommandHandler<*, *> {
        return commandProviderMap[commandClass]!!.get()
    }


    internal fun <R, C : Query<R>> getQuery(commandClass: Class<C>): QueryHandler<*, *> {
        return queryProviderMap[commandClass]!!.get()
    }
}

