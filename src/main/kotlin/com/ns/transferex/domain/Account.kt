package com.ns.transferex.domain

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Account(@Id @GeneratedValue(strategy = GenerationType.AUTO)
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
}