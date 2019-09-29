package com.ns.transferex.infrastructure.persistance

import com.ns.transferex.domain.Transaction
import com.ns.transferex.domain.TransactionRepository
import java.util.*
import javax.inject.Singleton
import javax.persistence.EntityManager

@Singleton
open class TransactionRepositoryImp(private val entityManager: EntityManager) : TransactionRepository {

    override fun insert(entity: Transaction) {
        entityManager.persist(entity)
    }

    override fun findById(id: Int): Optional<Transaction> {
        return Optional.ofNullable(entityManager
                .find(Transaction::class.java, id))
    }

}