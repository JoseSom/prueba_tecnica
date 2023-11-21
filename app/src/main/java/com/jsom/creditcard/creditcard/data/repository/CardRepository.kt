package com.jsom.creditcard.creditcard.data.repository

import com.jsom.creditcard.core.network.RetrofitClient
import com.jsom.creditcard.creditcard.service.CreditCardService
import com.jsom.creditcard.creditcard.service.MovementsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardRepository {
    private val cardService = RetrofitClient.retrofit.create(CreditCardService::class.java)
    private val movementsService = RetrofitClient.retrofit.create(MovementsService::class.java)

    suspend fun getCreditCardInfo() = withContext(Dispatchers.IO){
        kotlin.runCatching { cardService.getCreditCardInfo().body() }
    }

    suspend fun getMovementsInfo() = withContext(Dispatchers.IO){
        kotlin.runCatching { movementsService.getMovementList().body() }
    }
}