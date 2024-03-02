package com.example.uaedulntucw4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.uaedulntucw4.ui.theme.Uaedulntucw4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Uaedulntucw4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FirstScreen()
                }
            }
        }
    }
}

@Composable
fun FirstScreen() {
    Text(
        text = "This is the first screen",
        modifier = Modifier.fillMaxSize()
    ) {
        // Navigate to the second screen when clicked
        navigateToSecondScreen()
    }
}

@Composable
fun SecondScreen() {
    Text(
        text = "This is the second screen",
        modifier = Modifier.fillMaxSize()
    ) {
        // Navigate back to the first screen when clicked
        navigateBackToFirstScreen()
    }
}

@Composable
fun navigateToSecondScreen() {
    // Replace the content with the SecondScreen composable when navigating
    SecondScreen()
}

@Composable
fun navigateBackToFirstScreen() {
    // Replace the content with the FirstScreen composable when navigating back
    FirstScreen()
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    Uaedulntucw4Theme {
        FirstScreen()
    }
}
