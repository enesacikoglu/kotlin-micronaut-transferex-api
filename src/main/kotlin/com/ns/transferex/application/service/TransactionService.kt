package com.ns.transferex.application.service

import com.ns.transferex.domain.Transaction

interface TransactionService {
    fun save(transaction: Transaction)
    fun getTransactionById(id: Int): Transaction
}