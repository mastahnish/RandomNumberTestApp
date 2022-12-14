package com.modemobile.jacekrys.randomnumbertestapp.presentation.checknumber

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modemobile.jacekrys.randomnumbertestapp.common.Constants
import com.modemobile.jacekrys.randomnumbertestapp.common.Resource
import com.modemobile.jacekrys.randomnumbertestapp.domain.usecase.getrandomnumber.GetRandomNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RandomNumberCheckViewModel @Inject constructor(
    private val getRandomNumberUseCase: GetRandomNumberUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CheckNumberState())
    val state: State<CheckNumberState> = _state

    init {
        val savedRandomNumber = getSavedRandomNumber()?.let{
            _state.value = CheckNumberState(randomNumber = it)
        }

        if (savedRandomNumber == null) {
            getRandomNumber()
        }
    }

    private fun getRandomNumber() {
        getRandomNumberUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    savedStateHandle[Constants.FETCHED_RANDOM_NUMBER_KEY] = result.data
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

    private fun getSavedRandomNumber() : Int? = savedStateHandle.get<Int>(Constants.FETCHED_RANDOM_NUMBER_KEY)

    fun checkUserNumber(userNumber : Int) {
        val isNumberMatched = getSavedRandomNumber()?.equals(userNumber) ?: false
        _state.value = CheckNumberState(randomNumber = getSavedRandomNumber(), isNumberMatched = isNumberMatched)
        savedStateHandle[Constants.FETCHED_RANDOM_NUMBER_KEY] = null
        getRandomNumber()
    }
}
