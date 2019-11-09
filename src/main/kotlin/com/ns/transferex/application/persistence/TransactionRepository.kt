package com.ns.transferex.application.persistence

import com.ns.transferex.domain.Transaction
import java.util.*

interface TransactionRepository {
    fun save(entity: Transaction)
    fun findById(id: Int): Optional<Transaction>
}