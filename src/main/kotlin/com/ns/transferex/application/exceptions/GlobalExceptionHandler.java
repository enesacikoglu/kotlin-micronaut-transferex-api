package com.ns.transferex.application.exceptions;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import org.hibernate.StaleObjectStateException;
import org.springframework.dao.ConcurrencyFailureException;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {Exception.class, ExceptionHandler.class})
public class GlobalExceptionHandler implements ExceptionHandler<Exception, HttpResponse<?>> {

    @Override
    public HttpResponse<?> handle(HttpRequest request, Exception exception) {
        if (exception.getClass().equals(ConcurrencyFailureException.class)
                || exception.getClass().equals(StaleObjectStateException.class))
            return HttpResponse.status(HttpStatus.LOCKED, "Account has already in process with another transaction,try again!");
        else if (exception.getClass().equals(DomainNotFoundException.class))
            return HttpResponse.status(HttpStatus.NOT_FOUND, exception.getMessage());
        else if (exception.getClass().equals(BusinessException.class))
            return HttpResponse.status(HttpStatus.BAD_REQUEST, exception.getMessage());
        else
            return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected system Error!");
    }
}