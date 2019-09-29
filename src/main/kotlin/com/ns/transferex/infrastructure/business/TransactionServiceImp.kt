package com.ns.transferex.infrastructure.business

import com.ns.transferex.application.exceptions.DomainNotFoundException
import com.ns.transferex.application.service.TransactionService
import com.ns.transferex.domain.Transaction
import com.ns.transferex.domain.TransactionRepository
import io.micronaut.spring.tx.annotation.Transactional
import javax.inject.Singleton

@Singleton
open class TransactionServiceImp(private val transactionRepository: TransactionRepository) : TransactionService {

    override fun getTransactionById(id: Int): Transaction {
        return transactionRepository.findById(id)
                .orElseThrow { throw DomainNotFoundException("transaction.not.found") }
    }

    @Transactional
    override fun save(transaction: Transaction) {
        transactionRepository.insert(transaction)
    }

}