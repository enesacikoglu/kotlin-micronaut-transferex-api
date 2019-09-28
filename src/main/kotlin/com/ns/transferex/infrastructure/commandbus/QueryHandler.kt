package com.trendyol.productcontenteditorapi.infra.commandbus

interface QueryHandler<R, Q : Query<R>> {
    suspend fun handleAsync(query: Q): R
}