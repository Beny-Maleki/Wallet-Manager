package com.example.wallet_manager.repositories

import com.example.wallet_manager.model.entities.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: JpaRepository<Account, Long> {
    fun findAccountById(id: Long): Account
    fun existsAccountByOwner(owner: String): Boolean
}