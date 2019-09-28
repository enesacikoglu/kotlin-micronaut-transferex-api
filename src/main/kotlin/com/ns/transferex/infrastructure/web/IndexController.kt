package com.ns.transferex.infrastructure.web

import com.ns.transferex.infrastructure.web.conf.SwaggerConfig
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.views.View
import io.swagger.v3.oas.annotations.Hidden
import javax.inject.Inject


@Hidden
@Controller
class IndexController(@Inject private val config: SwaggerConfig) {

    @View("swagger/index")
    @Get("/swagger-ui.html")
    fun index() = config

    @View("swagger/index")
    @Get("/swagger-ui")
    fun indexRedirect() = config

}