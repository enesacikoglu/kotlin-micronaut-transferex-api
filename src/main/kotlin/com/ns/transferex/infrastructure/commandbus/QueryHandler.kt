package com.ns.transferex.infrastructure.commandbus

interface QueryHandler<R, Q : Query<R>> {
    suspend fun handleAsync(query: Q): R
}