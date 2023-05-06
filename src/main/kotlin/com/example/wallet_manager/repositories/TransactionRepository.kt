package com.example.wallet_manager.repositories

import com.example.wallet_manager.model.entities.AccountDocument
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository
import javax.annotation.Nullable

@Repository
interface TransactionRepository: ElasticsearchRepository<AccountDocument, String> {
    @Nullable
    fun findAccountById(id: Long?): AccountDocument

    fun save(accountDocument: AccountDocument)
}