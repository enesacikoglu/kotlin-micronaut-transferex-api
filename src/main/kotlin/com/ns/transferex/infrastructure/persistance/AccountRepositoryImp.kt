package com.ns.transferex.infrastructure.persistance

import com.ns.transferex.domain.Account
import com.ns.transferex.domain.AccountRepository
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import javax.persistence.EntityManager

@Singleton
open class AccountRepositoryImp(@Inject
                                private val entityManager: EntityManager) : AccountRepository {
    override fun insert(entity: Account) {
        entityManager.persist(entity)
    }

    override fun findById(id: Int): Optional<Account> {
        return Optional.ofNullable(entityManager
                .find(Account::class.java, id))
    }

    override fun update(entity: Account): Account {
        return entityManager.merge(entity)
    }

}