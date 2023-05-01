package com.example.wallet_manager.model.entities

import com.example.wallet_manager.model.utils.enums.UpdateType
import jakarta.persistence.*

@Entity
@Table(name="transaction")
class Transaction {
    @Id
    @Column(name="transactionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var transactionId: Long? = null

    @Column(name="accountId")
    var accountId: Long? = null

    @Column(name="type")
    var type: UpdateType? = null

    @Column(name="amount")
    var amount: Long = 0
}