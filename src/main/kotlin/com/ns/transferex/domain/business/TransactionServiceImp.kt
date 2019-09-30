package com.ns.transferex.domain.business

import com.ns.transferex.application.exceptions.DomainNotFoundException
import com.ns.transferex.application.service.TransactionService
import com.ns.transferex.domain.Transaction
import com.ns.transferex.domain.TransactionRepository
import io.micronaut.spring.tx.annotation.Transactional
import javax.inject.Singleton

@Singleton
open class TransactionServiceImp(private val transactionRepository: TransactionRepository) : TransactionService {

    @Transactional(readOnly = true)
    override fun getAll(): List<Transaction> {
        return transactionRepository.findAll()
    }

    @Transactional(readOnly = true)
    override fun getTransactionById(id: Int): Transaction {
        return transactionRepository.findById(id)
                .orElseThrow { throw DomainNotFoundException("Transaction not found") }
    }

    @Transactional
    override fun save(transaction: Transaction) {
        transactionRepository.insert(transaction)
    }

    @Transactional
    override fun update(transaction: Transaction): Transaction {
        return transactionRepository.update(transaction)
    }
}