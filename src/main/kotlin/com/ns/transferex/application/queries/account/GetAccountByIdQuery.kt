package com.ns.transferex.application.queries.account

import com.ns.transferex.application.commandbus.Query

data class GetAccountByIdQuery(val accountId: Int) : Query<GetAccountByIdResponse?>
