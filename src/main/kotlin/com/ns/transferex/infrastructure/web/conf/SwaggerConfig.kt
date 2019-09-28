package com.ns.transferex.infrastructure.web.conf

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("application.api.swagger")
class SwaggerConfig(val version: String,
                    var layout: String,
                    var isDeepLinking: Boolean = false,
                    var urls: List<URIConfig>? = listOf()) {

    @ConfigurationProperties("urls")
    class URIConfig(var name: String?, var url: String?)
}