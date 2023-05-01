package com.example.wallet_manager.model.entities

import com.example.wallet_manager.model.utils.enums.AnswerStatus

class Message(val accounts: List<Account>? = null, val status: AnswerStatus, info: String)