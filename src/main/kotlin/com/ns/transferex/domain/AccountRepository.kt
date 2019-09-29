package com.ns.transferex.domain

import java.util.*


interface AccountRepository {
    fun findById(id: Int): Optional<Account>
    fun insert(entity: Account)
    fun update(entity: Account): Account
    fun findAll(): List<Account>
}