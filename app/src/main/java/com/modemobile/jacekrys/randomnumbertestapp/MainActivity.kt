package com.modemobile.jacekrys.randomnumbertestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.modemobile.jacekrys.randomnumbertestapp.presentation.checknumber.RandomNumberScreen
import com.modemobile.jacekrys.randomnumbertestapp.presentation.ui.theme.RandomNumberAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomNumberAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RandomNumberScreen()
                }
            }
        }
    }
}
