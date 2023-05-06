package com.example.wallet_manager.model.entities

import com.example.wallet_manager.dto.AccountUpdateRequest
import com.example.wallet_manager.model.utils.enums.UpdateType
import jakarta.persistence.*
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "transaction")
class TransactionDocument {

    // this property is mapped to "_id" internal property of elasticsearch, thus, automatically generated:
    // https://stackoverflow.com/questions/45431574/not-auto-generating-id-for-long-type-but-for-string-type-field-in-spring-data-el
    @Id
    var transactionId: String? = null

    var accountId: Long? = null

    var type: UpdateType? = null

    var amount: Long = 0

    fun fillPropertiesWith(accountUpdateRequest: AccountUpdateRequest) {
        this.accountId = accountUpdateRequest.accountId
        this.amount = accountUpdateRequest.amount
        this.type = accountUpdateRequest.type
    }
}