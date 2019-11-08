package com.ns.transferex.infrastructure.persistence

import com.ns.transferex.domain.Account
import com.ns.transferex.application.persistence.AccountRepository
import java.util.*
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.LockModeType

@Singleton
open class AccountRepositoryImp(private val entityManager: EntityManager) : AccountRepository {

    override fun save(entity: Account) {
        entityManager.persist(entity)
    }

    override fun findById(id: Int): Optional<Account> {
        return Optional.ofNullable(entityManager
                .find(Account::class.java, id))
    }
}