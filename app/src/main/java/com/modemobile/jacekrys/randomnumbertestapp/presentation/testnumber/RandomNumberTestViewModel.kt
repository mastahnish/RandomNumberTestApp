package com.modemobile.jacekrys.randomnumbertestapp.presentation.testnumber

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
class RandomNumberTestViewModel @Inject constructor(
    private val getRandomNumberUseCase: GetRandomNumberUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(TestNumberState())
    val state: State<TestNumberState> = _state

    init {
        val savedRandomNumber = savedStateHandle.get<Int>(Constants.FETCHED_RANDOM_NUMBER_KEY)?.let{
            _state.value = TestNumberState(randomNumber = it)
        }

        if (savedRandomNumber == null) {
            getRandomNumber()
        }
    }

    private fun getRandomNumber() {
        getRandomNumberUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = TestNumberState(randomNumber = result.data)
                    savedStateHandle[Constants.FETCHED_RANDOM_NUMBER_KEY] = result.data
                }
                is Resource.Error -> {
                    _state.value = TestNumberState(
                        error = result.message ?: "Some unexpected error occurred."
                    )
                }
                is Resource.Loading -> {
                    _state.value = TestNumberState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun clearSavedRandomNumber() {
        savedStateHandle[Constants.FETCHED_RANDOM_NUMBER_KEY] = null
    }
}