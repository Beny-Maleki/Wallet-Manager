package com.example.wallet_manager.service

import com.example.wallet_manager.model.Account
import com.example.wallet_manager.model.AccountNotFound
import com.example.wallet_manager.model.NotAnyAccountExist
import com.example.wallet_manager.model.UpdateType
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
        if (db.count() > 0) {
            return db.findAll().toList()
        } else {
            throw NotAnyAccountExist()
        }

    }

    fun getAccountById(id: Long): Account {
        if (db.existsById(id)) {
            return db.findAccountById(id)
        } else {
            throw AccountNotFound(id)
        }

    }

    // UPDATE type services
    fun updateAccountBalanceById(id: Long, type: UpdateType, amount: Long) {
        if (db.existsById(id)) {
            val account: Account = db.findAccountById(id)
            if (type == UpdateType.DECREASE) {
                account.balance -= amount
            } else if (type == UpdateType.INCREASE) {
                account.balance += amount
            }
            db.save(account)
        } else {
            throw AccountNotFound(id)
        }
    }

    // DELETE type services
    fun deleteAccountById(id: Long) {
        if(db.existsById(id)) {
            db.deleteById(id)
        } else {
            throw AccountNotFound(id)
        }
    }


}