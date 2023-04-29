package com.example.wallet_manager.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController {
    @GetMapping("/index")
    fun greeting(@RequestParam("name") name: String): String {
        return "Hello $name! Welcome to your Account Manager!"
    }
}