package com.modemobile.jacekrys.randomnumbertestapp.presentation.checknumber

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modemobile.jacekrys.randomnumbertestapp.common.Constants.FETCHED_RANDOM_NUMBER_KEY
import com.modemobile.jacekrys.randomnumbertestapp.common.Constants.NUMBER_UNDEFINED
import com.modemobile.jacekrys.randomnumbertestapp.common.Resource
import com.modemobile.jacekrys.randomnumbertestapp.domain.usecase.getrandomnumber.GetRandomNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RandomNumberCheckViewModel @Inject constructor(
    private val getRandomNumberUseCase: GetRandomNumberUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _state = mutableStateOf(CheckNumberState())
    val state: State<CheckNumberState> = _state

    init {
        _state.value = CheckNumberState(randomNumber = getSavedRandomNumber())
    }

    fun getRandomNumber() {
        getRandomNumberUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    sharedPreferences.edit().putInt(FETCHED_RANDOM_NUMBER_KEY, result.data ?: NUMBER_UNDEFINED).apply()
                    _state.value = CheckNumberState(isLoading = false, randomNumber = result.data)
                }
                is Resource.Error -> {
                    _state.value = CheckNumberState(
                        error = result.message ?: "Some unexpected error occurred."
                    )
                }
                is Resource.Loading -> {
                    _state.value = CheckNumberState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSavedRandomNumber() : Int = sharedPreferences.getInt(FETCHED_RANDOM_NUMBER_KEY, NUMBER_UNDEFINED)

    fun checkUserNumber(number : String) {
        val numberInt = if(number.isNotBlank()) number.toInt() else Int.MIN_VALUE
        val isNumberMatched = getSavedRandomNumber() == numberInt

        _state.value = CheckNumberState(randomNumber = getSavedRandomNumber(), isNumberMatched = isNumberMatched, isNumberChecked = true)
    }
}
