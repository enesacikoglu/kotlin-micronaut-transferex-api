package com.ns.transferex.application.persistence

import com.ns.transferex.domain.Account
import java.util.*


interface AccountRepository {
    fun findById(id: Int): Optional<Account>
    fun save(entity: Account)
}