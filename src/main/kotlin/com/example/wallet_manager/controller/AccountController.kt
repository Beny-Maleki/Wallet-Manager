package com.example.wallet_manager.controller

import com.example.wallet_manager.model.*
import com.example.wallet_manager.service.AccountService
import org.springframework.web.bind.annotation.*

@RestController
class AccountController(val service: AccountService){
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
    fun updateAccountById(@PathVariable(value= "id") id: Long, @RequestBody type: UpdateType, @RequestBody amount: Long): Message {
        return try {
            service.updateAccountBalance(id, type, amount)
        } catch (e: AccountNotFound) {
            Message(null, AnswerStatus.ERROR, e.message.toString())
        }
    }
}