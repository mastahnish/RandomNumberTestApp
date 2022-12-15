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
import com.modemobile.jacekrys.randomnumbertestapp.common.Constants.MAX_RANDOM_NUMBER
import com.modemobile.jacekrys.randomnumbertestapp.common.Constants.MIN_RANDOM_NUMBER
import com.modemobile.jacekrys.randomnumbertestapp.common.Constants.NUMBER_UNDEFINED
import com.modemobile.jacekrys.randomnumbertestapp.presentation.ui.theme.ColorPrimary
import com.modemobile.jacekrys.randomnumbertestapp.presentation.ui.theme.MediumGray

@Composable
fun CheckResult(
    randomNumber: Int,
    isNumberMatched: Boolean,
    isNumberChecked: Boolean
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (isNumberMatched) "You matched the number!" else "Try your number [$MIN_RANDOM_NUMBER - $MAX_RANDOM_NUMBER]",
            color = if (isNumberMatched) ColorPrimary else MediumGray,
            textAlign = TextAlign.Center,
            style = if (isNumberMatched) MaterialTheme.typography.h5 else MaterialTheme.typography.body1
        )

        Text(
            text = if (isNumberChecked && randomNumber != NUMBER_UNDEFINED) {
                "Current Random Number is $randomNumber"
            } else {
                if (randomNumber == NUMBER_UNDEFINED) "Number is not yet available. Please draw new number" else "Number is ready to check"
            },
            color = Color.Black,
            textAlign = TextAlign.Center,
            style =  if (randomNumber == NUMBER_UNDEFINED) MaterialTheme.typography.body1 else MaterialTheme.typography.h6
        )
    }
}
