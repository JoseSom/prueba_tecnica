package com.jsom.creditcard.creditcard.service

import com.jsom.creditcard.creditcard.data.model.CreditCard
import retrofit2.Response
import retrofit2.http.GET

interface CreditCardService {
    @GET("tarjetacredito.php/1")
    suspend fun getCreditCardInfo(): Response<CreditCard>
}