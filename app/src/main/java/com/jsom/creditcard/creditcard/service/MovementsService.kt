package com.jsom.creditcard.creditcard.service

import com.jsom.creditcard.creditcard.data.model.Movement
import retrofit2.Response
import retrofit2.http.GET

interface MovementsService {
    @GET("tarjetacredito-movimientos.php/3")
    suspend fun getMovementList(): Response<List<Movement>>
}