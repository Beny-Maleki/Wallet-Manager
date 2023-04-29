package com.example.wallet_manager.model

import jakarta.persistence.*

@Entity
@Table(name = "account")
open class Account {
    @Column(name = "owner", length = 64)
    var owner: String? = null

    @Column(name = "balance")
    var balance: Long = 0

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Long? = null
}

