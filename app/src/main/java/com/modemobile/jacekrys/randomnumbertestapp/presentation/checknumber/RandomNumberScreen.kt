package com.modemobile.jacekrys.randomnumbertestapp.presentation.checknumber

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.modemobile.jacekrys.randomnumbertestapp.common.Constants.NUMBER_UNDEFINED
import com.modemobile.jacekrys.randomnumbertestapp.presentation.checknumber.components.CheckResult

@Preview
@Composable
fun RandomNumberScreenPreview() {
    MaterialTheme {
        RandomNumberScreenUI(
            state = CheckNumberState(
                isLoading = false,
                randomNumber = 1,
                isNumberMatched = false,
                error = ""
            ),
            onCheckButtonClick = {},
            onDrawNewNumber = {}
        )
    }
}

@Composable
fun RandomNumberScreen(
    viewModel: RandomNumberCheckViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    RandomNumberScreenUI(
        state = state,
        onCheckButtonClick = { enteredValue -> viewModel.checkUserNumber(enteredValue) },
        onDrawNewNumber = { viewModel.getRandomNumber() })
}

@Composable
fun RandomNumberScreenUI(
    state: CheckNumberState,
    onCheckButtonClick: (enteredValue: String) -> Unit,
    onDrawNewNumber: () -> Unit
) {
    var textValue by remember { mutableStateOf(TextFieldValue()) }

    Box(modifier = Modifier.fillMaxSize()) {
        state.randomNumber?.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Center),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {

                    Text(
                        text = "Enter your guess number",
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Center)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    BasicTextField(
                        value = textValue,
                        modifier = Modifier
                            .border(2.dp, color = Color.Black)
                            .align(Center),
                        textStyle = TextStyle(fontSize = 56.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            textValue = it
                        },
                        enabled = state.randomNumber != NUMBER_UNDEFINED,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Button(
                            onClick = { onDrawNewNumber() }
                        ) {
                            Text(
                                text = "Draw new number",
                                color = MaterialTheme.colors.primaryVariant,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(horizontal = 20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = { onCheckButtonClick(textValue.text) }
                        ) {
                            Text(
                                text = "Check",
                                color = MaterialTheme.colors.primaryVariant,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(horizontal = 20.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    CheckResult(
                        randomNumber = state.randomNumber,
                        isNumberMatched = state.isNumberMatched,
                        isNumberChecked = state.isNumberChecked
                    )
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Center)
            )
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Center)
            )
        }
    }
}
