package com.ns.transferex.domain

import java.math.BigDecimal
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class Transaction(@Id @GeneratedValue(strategy = GenerationType.AUTO) private val id: Int,
                       private val fromAccount: Int,
                       private val toAccount: Int,
                       private val amount: BigDecimal)