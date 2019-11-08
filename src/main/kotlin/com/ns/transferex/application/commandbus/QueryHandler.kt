package com.ns.transferex.application.commandbus

interface QueryHandler<R, Q : Query<R>> {
    fun handle(query: Q): R
}