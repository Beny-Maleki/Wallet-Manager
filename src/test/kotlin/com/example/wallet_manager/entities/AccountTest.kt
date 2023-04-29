package com.example.wallet_manager.entities

import com.example.wallet_manager.model.Account
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.hibernate.testing.transaction.TransactionUtil.doInHibernate
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AccountTest {
    fun getCurrentSessionFromConfig(): SessionFactory? {
        // SessionFactory in Hibernate 5 example
        val config: Configuration = Configuration()
        config.configure()
        // local SessionFactory bean created
        val sessionFactory: SessionFactory = config.buildSessionFactory ()
        return sessionFactory
    }

        @Test
    fun givenPerson_whenSaved_thenFound() {
        doInHibernate(({ getCurrentSessionFromConfig() }), { session ->
            val accountToSave = Account()
            accountToSave.owner = "John"
            accountToSave.balance = 100
            session.persist(accountToSave)
            val accountFound = session.find(Account::class.java, accountToSave.id)
            session.refresh(accountFound)

            assertTrue(accountFound.owner == accountToSave.owner)
        })
    }
}