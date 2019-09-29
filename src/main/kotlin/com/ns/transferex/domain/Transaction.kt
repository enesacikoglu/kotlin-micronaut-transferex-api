package com.ns.transferex.domain

import java.math.BigDecimal
import java.math.RoundingMode
import javax.persistence.*

@Entity
@Table(name = "Transaction")
data class Transaction(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                       var id: Int = 0,
                       var fromAccount: Int = 0,
                       var toAccount: Int = 0,
                       var amount: BigDecimal = BigDecimal.ZERO.setScale(2)) {

    companion object {
        fun new(fromAccount: Int, toAccount: Int, balance: BigDecimal): Transaction {
            return Transaction(0, fromAccount, toAccount, balance.setScale(2, RoundingMode.HALF_DOWN))
        }
    }
}