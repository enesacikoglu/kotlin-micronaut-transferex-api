package com.ns.transferex.domain

import com.ns.transferex.application.exceptions.BusinessException
import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Account(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                   private val id: Int = 0,
                   var owner: String = "",
                   var balance: BigDecimal = BigDecimal.ZERO,
                   @Version
                   private val version: Long = 0L) {

    companion object {
        fun new(owner: String, balance: BigDecimal): Account {
            return Account(0, owner, balance, 0L)
        }
    }

    fun add(amount: BigDecimal) {
        this.balance = this.balance.add(amount)
    }

    fun withdraw(amount: BigDecimal) {
        val currentBalance = this.balance.subtract(amount)
        if (currentBalance < BigDecimal.ZERO) {
            throw BusinessException("account.has.not.got.enough.balance")
        }
        this.balance = currentBalance
    }
}