package com.ns.transferex

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.ns.transferex")
                .mainClass(Application.javaClass)
                .start()
    }
}