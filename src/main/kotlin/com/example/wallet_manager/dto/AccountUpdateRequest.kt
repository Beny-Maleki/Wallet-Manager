package com.example.wallet_manager.dto

import com.example.wallet_manager.model.utils.enums.UpdateType
import com.fasterxml.jackson.annotation.JsonProperty

class AccountUpdateRequest (
        @JsonProperty("accountId")
        val accountId: Long?,
        @JsonProperty("type")
        val type: UpdateType,
        @JsonProperty("amount")
        val amount: Long
) {
        override fun toString(): String {
                return "accountId = $accountId, type=$type, amount=$amount"
        }
}