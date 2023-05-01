package com.example.wallet_manager.service

import com.example.wallet_manager.model.entities.Account
import com.example.wallet_manager.model.entities.Message
import com.example.wallet_manager.model.utils.exceptions.AccountAlreadyExists
import com.example.wallet_manager.model.utils.exceptions.AccountNotFound
import com.example.wallet_manager.model.utils.exceptions.NotAnyAccountExist
import com.example.wallet_manager.model.utils.enums.AnswerStatus
import com.example.wallet_manager.model.utils.enums.UpdateType
import com.example.wallet_manager.repositories.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import kotlin.jvm.Throws


@Service
class AccountService @Autowired constructor(
        val db: AccountRepository,
        val kafkaTemplate: KafkaTemplate<String, String>
) {

    //// CRUD services ////

    // CREATE type services
    @Throws(AccountAlreadyExists::class)
    fun registerNewAccount(owner: String, balance: Long): Message {
        if (db.existsAccountByOwner(owner)) {
            throw AccountAlreadyExists(owner)
        }
        val newAccount = Account()
        newAccount.owner = owner
        newAccount.balance = balance
        db.save(newAccount)
        return Message(null, AnswerStatus.SUCCESSFUL, "")
    }

    // READ type services
    @Throws(NotAnyAccountExist::class)
    fun getAllAccounts(): Message {
        if (db.count() > 0) {
            return Message(db.findAll().toList(), AnswerStatus.SUCCESSFUL, "")
        } else {
            throw NotAnyAccountExist()
        }

    }

    @Throws(AccountNotFound::class)
    fun getAccountById(id: Long): Message {
        if (db.existsById(id)) {
            return Message(listOf(db.findAccountById(id)), AnswerStatus.SUCCESSFUL, "")
        } else {
            throw AccountNotFound(id)
        }

    }

    // UPDATE type services
    @Throws(AccountNotFound::class)
    fun updateAccountBalance(id: Long, type: UpdateType, amount: Long): Message {
        if (db.existsById(id)) {
            val account: Account = db.findAccountById(id)
            if (type == UpdateType.DECREASE) {
                account.balance -= amount
            } else if (type == UpdateType.INCREASE) {
                account.balance += amount
            }
            db.save(account)
            return Message(null, AnswerStatus.SUCCESSFUL, "")
        } else {
            throw AccountNotFound(id)
        }
    }

    // DELETE type services
    @Throws(AccountNotFound::class)
    fun deleteAccountById(id: Long): Message {
        if(db.existsById(id)) {
            db.deleteById(id)
            return Message(null, AnswerStatus.SUCCESSFUL, "")
        } else {
            throw AccountNotFound(id)
        }
    }


}