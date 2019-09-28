package com.trendyol.productcontenteditorapi.application.queries

import com.ns.transferex.domain.models.GetAccountByIdResponse
import com.trendyol.productcontenteditorapi.infra.commandbus.Query

data class GetAccountByIdQuery(val accountId: Int) : Query<GetAccountByIdResponse?>
