package com.ns.transferex.infrastructure.persistance

import com.ns.transferex.domain.Transaction
import com.ns.transferex.domain.TransactionRepository
import java.util.*
import javax.inject.Singleton
import javax.persistence.EntityManager

@Singleton
open class TransactionRepositoryImp(private val entityManager: EntityManager) : TransactionRepository {

    override fun findAll(): List<Transaction> {
        return entityManager
                .createQuery("Select t from Transaction t", Transaction::class.java)
                .resultList
    }

    override fun insert(entity: Transaction) {
        entityManager.persist(entity)
    }

    override fun update(entity: Transaction): Transaction {
        return entityManager.merge(entity)
    }

    override fun findById(id: Int): Optional<Transaction> {
        return Optional.ofNullable(entityManager
                .find(Transaction::class.java, id))
    }

}