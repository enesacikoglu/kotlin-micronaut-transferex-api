package com.ns.transferex.application.exceptions

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import org.hibernate.StaleObjectStateException
import org.springframework.dao.ConcurrencyFailureException

import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [Exception::class, ExceptionHandler::class])
class GlobalExceptionHandler : ExceptionHandler<Exception, HttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: Exception): HttpResponse<*> {
        return if (exception.javaClass == ConcurrencyFailureException::class.java || exception.javaClass == StaleObjectStateException::class.java)
            HttpResponse.status<HttpStatus>(HttpStatus.LOCKED, "Account has already in process with another transaction,try again!")
        else if (exception.javaClass == DomainNotFoundException::class.java)
            HttpResponse.status<HttpStatus>(HttpStatus.NOT_FOUND, exception.message)
        else if (exception.javaClass == BusinessException::class.java)
            HttpResponse.status<HttpStatus>(HttpStatus.BAD_REQUEST, exception.message)
        else
            HttpResponse.status<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected system Error!")
    }
}