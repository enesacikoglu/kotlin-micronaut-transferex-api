package com.ns.transferex

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info


@OpenAPIDefinition(info = Info(title = "Transferex Api", version = "1.0",
        description = "Trusted Money Transfer Api ",
        contact = Contact(name = "Transferex", email = "enes.acikoglu@gmail.com")))
object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.ns.transferex")
                .mainClass(Application.javaClass)
                .start()
    }
}