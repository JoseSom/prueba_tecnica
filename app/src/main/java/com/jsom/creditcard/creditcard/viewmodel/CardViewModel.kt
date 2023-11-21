package com.jsom.creditcard.creditcard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsom.creditcard.creditcard.data.model.CreditCard
import com.jsom.creditcard.creditcard.data.model.Movement
import com.jsom.creditcard.creditcard.data.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardViewModel : ViewModel() {
    private val cardRepository = CardRepository()

    private val _creditCardInfo = MutableLiveData<CreditCard>()
    val creditCardInfo: LiveData<CreditCard>
        get() = _creditCardInfo

    private val _movementsList = MutableLiveData<List<Movement>>()
    val movementsList: LiveData<List<Movement>>
        get() = _movementsList


    fun getCardInfo(){
        viewModelScope.launch(Dispatchers.IO){
            val response = cardRepository.getCreditCardInfo().getOrNull()
            response?.let { creditCard -> _creditCardInfo.postValue(creditCard) }
        }
    }

    fun getMovements(){
        viewModelScope.launch(Dispatchers.IO){
            val response = cardRepository.getMovementsInfo().getOrNull()
            response?.let { movements -> _movementsList.postValue(movements) }
        }
    }
}