package com.modemobile.jacekrys.randomnumbertestapp.presentation.checknumber.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.modemobile.jacekrys.randomnumbertestapp.presentation.ui.theme.ColorPrimary
import com.modemobile.jacekrys.randomnumbertestapp.presentation.ui.theme.MediumGray

@Composable
fun CheckResult(
    isNumberMatched: Boolean,
    randomNumber: Int
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Current Random Number is $randomNumber",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6
        )
        Text(
            text = if (isNumberMatched ) "You matched the number!" else "Try again",
            color = if (isNumberMatched) ColorPrimary else MediumGray,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
    }
}
