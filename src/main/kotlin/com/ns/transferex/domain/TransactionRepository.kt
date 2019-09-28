package com.ns.transferex.domain

import java.util.*

interface TransactionRepository {
    fun insert(entity: Transaction)
    fun findById(id: Int): Optional<Transaction>
}