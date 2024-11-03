package com.example.hw3

import TextBoxViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.SavedStateHandle
import com.example.hw3.ui.theme.Hw3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Hw3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TextInputScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TextInputScreen(
    modifier: Modifier = Modifier,
    viewModel: TextBoxViewModel = viewModel(factory = TextBoxViewModel.provideFactory(SavedStateHandle()))
) {
    // Display a TextField that reads and writes to the ViewModel's text state
    TextField(
        value = viewModel.text,
        onValueChange = { viewModel.onTextChange(it) },
        label = { Text("Enter text") },
        modifier = modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun TextInputScreenPreview() {
    Hw3Theme {
        TextInputScreen()
    }
}
