package com.example.wallet_manager.model.entities

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "account")
class AccountDocument {
    @Id
    var id: Long? = null

    var owner: String? = null

    var balance: Long = 0

    var transactions = mutableListOf<TransactionDocument>()

    override fun toString(): String {
        return "id: $id, owner:$owner, balance:$balance\ntransactions: $transactions"
    }
}