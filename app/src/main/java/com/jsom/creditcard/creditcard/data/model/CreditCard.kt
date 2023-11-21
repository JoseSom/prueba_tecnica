package com.jsom.creditcard.creditcard.data.model

import android.text.TextUtils.split
import com.google.gson.annotations.SerializedName
import com.jsom.creditcard.core.util.capitalizeFirstLetter

typealias cardNumber = String
typealias cardHolder = String

data class CreditCard(
    @SerializedName("pkTarjetaCreditoID")
    val id: Long,
    @SerializedName("Nombre_Banco")
    val bankName: String,
    @SerializedName("Numero_Tarjeta")
    val cardNumber: cardNumber,
    @SerializedName("Titular_Tarjeta")
    val cardHolder: cardHolder,
    @SerializedName("Fecha_Exp")
    val expirationDate: String,
    @SerializedName("CVV")
    val cvv: Int,
    @SerializedName("Monto")
    val mount: Double
){
    companion object{
        fun splitCardNumber(cardNumber: cardNumber) = cardNumber.split("-")
        fun formatName(cardHolder: cardHolder) = cardHolder.split(" ").joinToString(" ") { it.capitalizeFirstLetter() }
    }
}
