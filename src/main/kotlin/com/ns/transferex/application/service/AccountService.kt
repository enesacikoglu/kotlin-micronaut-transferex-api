package com.ns.transferex.application.service

import com.ns.transferex.domain.Account

interface AccountService {
    fun save(account: Account)
    fun update(account: Account): Account
    fun getAccountById(id: Int): Account
    fun getAll(): List<Account>
}