package com.ns.transferex.domain

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Transaction(@Id @GeneratedValue(strategy = GenerationType.AUTO) private val id: Int,
                       var fromAccount: Int,
                       var toAccount: Int,
                       var amount: BigDecimal) {

    companion object {
        fun new(fromAccount: Int, toAccount: Int, balance: BigDecimal): Transaction {
            return Transaction(0, fromAccount, toAccount, balance)
        }
    }
}