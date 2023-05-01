package com.example.wallet_manager.controller

import com.example.wallet_manager.model.UpdateType

class AccountUpdateRequest (
        val type: UpdateType,
        val amount: Long
)