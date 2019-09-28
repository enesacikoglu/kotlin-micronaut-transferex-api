package com.ns.transferex.infrastructure.business

import com.ns.transferex.application.service.AccountService
import com.ns.transferex.domain.Account
import com.ns.transferex.domain.AccountRepository
import io.micronaut.spring.tx.annotation.Transactional
import javax.inject.Singleton

@Singleton
open class AccountServiceImp(private val accountRepository: AccountRepository) : AccountService {

    @Transactional
    override fun save(account: Account) {
        accountRepository.insert(account)
    }

    @Transactional
    override fun update(account: Account) = accountRepository.update(account)

    @Transactional(readOnly = true)
    override fun getAccountById(id: Int): Account {
        return accountRepository.findById(id)
                .orElseThrow { throw RuntimeException("account not found!") }
    }
}