package com.ns.transferex.application.exceptions;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = { BusinessException.class })
public class BusinessExceptionHandler implements ExceptionHandler<BusinessException, HttpResponse<?>> {

	@Override
	public HttpResponse<?> handle(HttpRequest request, BusinessException exception) {
		return HttpResponse.badRequest((exception.getMessage()));
	}
}