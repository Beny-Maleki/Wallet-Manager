package com.example.wallet_manager.controller

import com.example.wallet_manager.dto.AccountCreateRequest
import com.example.wallet_manager.dto.AccountUpdateRequest
import com.example.wallet_manager.model.*
import com.example.wallet_manager.model.entities.Message
import com.example.wallet_manager.model.utils.exceptions.AccountAlreadyExists
import com.example.wallet_manager.model.utils.exceptions.AccountNotFound
import com.example.wallet_manager.model.utils.exceptions.NotAnyAccountExist
import com.example.wallet_manager.model.utils.enums.AnswerStatus
import com.example.wallet_manager.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class AccountController @Autowired constructor( val service: AccountService){
    @GetMapping("/index")
    fun greeting(@RequestParam("name") name: String): String {
        return "Hello $name! Welcome to your Account Manager!"
    }

    @GetMapping("/get/all-accounts")
    fun getAllAccounts(): Message {
        return try {
            service.getAllAccounts()
        } catch (e: NotAnyAccountExist) {
            Message(null, AnswerStatus.ERROR, e.message.toString())
        }
    }

    @GetMapping("/get/account/{id}")
    fun getAccountById(@PathVariable(value = "id") id: Long): Message {
        return try {
            service.getAccountById(id)
        } catch (e: AccountNotFound) {
            Message(null, AnswerStatus.ERROR, e.message.toString())
        }
    }

    @PutMapping("/update/account/")
    fun updateAccountById(@RequestBody body: AccountUpdateRequest): Message {
        return try {
            service.updateAccountBalance(body)
        } catch (e: AccountNotFound) {
            Message(null, AnswerStatus.ERROR, e.message.toString())
        }
    }

    @PostMapping("/add/account")
    fun registerAccountById(@RequestBody body: AccountCreateRequest): Message {
        return try {
            service.registerNewAccount(body.owner, body.balance)
        } catch (e: AccountAlreadyExists) {
            Message(null, AnswerStatus.ERROR, e.message.toString())
        }
    }

    @DeleteMapping("/delete/account/{id}")
    fun deleteAccountById(@PathVariable(value = "id") id: Long): Message {
        return try {
            service.deleteAccountById(id)
        } catch (e: AccountNotFound) {
            Message(null, AnswerStatus.ERROR, e.message.toString())
        }
    }
}