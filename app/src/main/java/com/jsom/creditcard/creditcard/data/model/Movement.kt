package com.jsom.creditcard.creditcard.data.model

import com.google.gson.annotations.SerializedName

data class Movement(
    @SerializedName("pkMovimientosID")
    val id: Long,
    @SerializedName("Descripcion")
    val description: String,
    @SerializedName("Fecha")
    val date: String,
    @SerializedName("Monto")
    val mount: String,
)
