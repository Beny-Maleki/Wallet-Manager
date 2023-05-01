package com.example.wallet_manager.model

import jakarta.persistence.*

@Entity
@Table(name = "account")
class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "owner", length = 64)
    var owner: String? = null

    @Column(name = "balance")
    var balance: Long = 0
}

