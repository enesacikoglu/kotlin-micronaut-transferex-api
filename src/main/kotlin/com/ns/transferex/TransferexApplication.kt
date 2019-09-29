package com.ns.transferex

import io.micronaut.runtime.Micronaut

object TransferexApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.ns.transferex")
                .mainClass(TransferexApplication.javaClass)
                .start()
    }
}