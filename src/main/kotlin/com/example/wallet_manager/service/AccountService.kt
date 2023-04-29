package com.example.wallet_manager.service

import com.example.wallet_manager.model.Account
import com.example.wallet_manager.repositories.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(val db: AccountRepository) {
    //// CRUD services ////
    // CREATE type services
    fun registerNewAccount(owner: String) {
        val newAccount: Account = Account()
        newAccount.owner = owner
        db.save(newAccount)
    }

    // READ type services
    fun getAllAccounts(): List<Account> {
        return db.findAll().toList()
    }

    fun getAccountById(id: Long): Account {
        return db.findAccountById(id)
    }

    // UPDATE type services
    fun updateAccountById(id: Long, type: ) {
        db.save(id, )
    }


}