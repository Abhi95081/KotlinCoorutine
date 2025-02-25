package com.example.kotlincoorutine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlincoorutine.ui.theme.KotlinCoorutineTheme
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinCoorutineTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoroutineExample(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CoroutineExample(modifier: Modifier = Modifier) {
    var coroutineJob by remember { mutableStateOf<Job?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        Button(onClick = {
            coroutineJob?.cancel() // Cancel existing job if running
            coroutineJob = coroutineScope.launch {
                Log.d("Coroutine", "Created & Active")
                delay(2000) // Suspended
                Log.d("Coroutine", "Resumed & Running")
                delay(2000) // Suspended again
                Log.d("Coroutine", "Completed")
            }
        }) {
            Text("Start Coroutine")
        }

        Button(onClick = {
            coroutineJob?.cancel()
            Log.d("Coroutine", "Cancelled")
        }) {
            Text("Cancel Coroutine")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCoroutineExample() {
    KotlinCoorutineTheme {
        CoroutineExample()
    }
}
