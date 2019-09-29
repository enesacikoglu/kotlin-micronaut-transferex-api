package com.ns.transferex.infrastructure.business

import com.ns.transferex.application.exceptions.BusinessException
import com.ns.transferex.application.exceptions.DomainNotFoundException
import com.ns.transferex.application.service.TransferService
import com.ns.transferex.domain.AccountRepository
import com.ns.transferex.domain.Transaction
import com.ns.transferex.domain.TransactionRepository
import io.micronaut.retry.annotation.Retryable
import io.micronaut.spring.tx.annotation.Transactional
import org.springframework.dao.ConcurrencyFailureException
import javax.inject.Singleton

@Singleton
open class TransferServiceImp(private val accountRepository: AccountRepository,
                              private val transactionRepository: TransactionRepository) : TransferService {

    @Transactional
    @Retryable(includes = [ConcurrencyFailureException::class])
    override fun applyTransfer(transaction: Transaction) {

        val fromAccount = accountRepository.findById(transaction.fromAccount)
                .orElseThrow { throw DomainNotFoundException("account.not.found") }
        val toAccount = accountRepository.findById(transaction.toAccount)
                .orElseThrow { throw DomainNotFoundException("account.not.found") }

        if (fromAccount == toAccount) {
            throw BusinessException("transfer.must.be.between.different.accounts")
        }

        fromAccount.withdraw(transaction.amount)
        toAccount.add(transaction.amount)
        accountRepository.update(fromAccount)
        accountRepository.update(toAccount)
        transactionRepository.insert(transaction)
    }

}