package com.example.wallet_manager.controller

import com.example.wallet_manager.model.*
import com.example.wallet_manager.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
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

    @PutMapping("/update/account/{id}")
    fun updateAccountById(@PathVariable(value= "id") id: Long, @RequestBody body: AccountUpdateRequest): Message {
        return try {
            service.updateAccountBalance(id, body.type, body.amount)
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

    @DeleteMapping("/delete/{id}")
    fun deleteAccountById(@PathVariable(value = "id") id: Long): Message {
        return try {
            service.deleteAccountById(id)
        } catch (e: AccountNotFound) {
            Message(null, AnswerStatus.ERROR, e.message.toString())
        }
    }
}