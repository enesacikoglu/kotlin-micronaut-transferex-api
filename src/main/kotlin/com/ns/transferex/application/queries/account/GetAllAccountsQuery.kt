package com.ns.transferex.application.queries.account

import com.ns.transferex.domain.models.GetAccountByIdResponse
import com.ns.transferex.infrastructure.commandbus.Query

class GetAllAccountsQuery : Query<List<GetAccountByIdResponse>>