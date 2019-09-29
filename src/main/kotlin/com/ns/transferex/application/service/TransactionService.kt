package com.ns.transferex.application.service

import com.ns.transferex.domain.Transaction

interface TransactionService {
    fun getTransactionById(id: Int): Transaction
    fun save(transaction: Transaction)
    fun update(transaction: Transaction): Transaction
    fun getAll(): List<Transaction>
}