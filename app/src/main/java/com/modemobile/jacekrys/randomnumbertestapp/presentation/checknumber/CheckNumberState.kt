package com.modemobile.jacekrys.randomnumbertestapp.presentation.checknumber

class CheckNumberState(
    val isLoading: Boolean = false,
    val randomNumber: Int? = null,
    val isNumberMatched : Boolean = false,
    val error: String = ""
)
