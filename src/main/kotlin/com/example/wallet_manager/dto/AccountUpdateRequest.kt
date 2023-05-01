package com.example.wallet_manager.dto

import com.example.wallet_manager.model.utils.enums.UpdateType

class AccountUpdateRequest (
        val type: UpdateType,
        val amount: Long
)