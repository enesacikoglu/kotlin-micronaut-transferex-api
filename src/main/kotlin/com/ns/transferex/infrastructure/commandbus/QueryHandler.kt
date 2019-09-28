package com.ns.transferex.infrastructure.commandbus

interface QueryHandler<R, Q : Query<R>> {
    fun handle(query: Q): R
}