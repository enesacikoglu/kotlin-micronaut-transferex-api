package com.ns.transferex.domain

import java.util.*

interface TransactionRepository {
    fun insert(entity: Transaction)
    fun update(entity: Transaction): Transaction
    fun findById(id: Int): Optional<Transaction>
    fun findAll(): List<Transaction>
}