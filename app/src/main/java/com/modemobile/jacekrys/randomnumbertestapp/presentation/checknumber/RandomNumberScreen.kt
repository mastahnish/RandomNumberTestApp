package com.modemobile.jacekrys.randomnumbertestapp.presentation.checknumber

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
            onCheckButtonClick = {}
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
        onCheckButtonClick = { enteredValue -> viewModel.checkUserNumber(enteredValue) })
}

@Composable
fun RandomNumberScreenUI(
    state: CheckNumberState,
    onCheckButtonClick: (enteredValue: Int) -> Unit
) {
    var textValue by remember { mutableStateOf(TextFieldValue("Enter your guess number")) }

    Box(modifier = Modifier.fillMaxSize()) {
        state.randomNumber?.let {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().align(Center),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {

                    BasicTextField(
                        value = textValue,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            textValue = it
                        }
                    )

                    Button(
                        onClick = { onCheckButtonClick(textValue.text.toInt()) },
                    ) {
                        Text(
                            text = "Check",
                            color = MaterialTheme.colors.primaryVariant,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    CheckResult(
                        isNumberMatched = state.isNumberMatched,
                        randomNumber = state.randomNumber
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
                    .padding(horizontal = 20.dp).align(Center)
            )
        }
    }
}
