package com.example.wallet_manager.service

import com.example.wallet_manager.dto.AccountUpdateRequest
import com.example.wallet_manager.model.entities.AccountDocument
import com.example.wallet_manager.model.entities.TransactionDocument
import com.example.wallet_manager.model.utils.enums.UpdateType
import com.example.wallet_manager.model.utils.exceptions.AccountNotFound
import com.example.wallet_manager.repositories.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService (
        private val db: TransactionRepository,
) {
    fun newTransaction(accountUpdateRequest: AccountUpdateRequest) {
        val transactionDocument = TransactionDocument()
        transactionDocument.fillPropertiesWith(accountUpdateRequest)
        var accountDocument: AccountDocument

        accountDocument = db.findAccountById(accountUpdateRequest.accountId)

        if (accountDocument == null) {
            accountDocument = AccountDocument()
            accountDocument.id = accountUpdateRequest.accountId
            accountDocument.balance = 0
        }

        try {
            // Update account balance in elastic
            if (accountUpdateRequest.type == UpdateType.DECREASE) {
                accountDocument.balance -= accountUpdateRequest.amount
            } else if (accountUpdateRequest.type == UpdateType.INCREASE) {
                accountDocument.balance += accountUpdateRequest.amount
            }

            accountDocument.transactions.add(transactionDocument)
            db.save(accountDocument)
        } catch (e: Exception) {
            print("Here2")
        }


    }
}