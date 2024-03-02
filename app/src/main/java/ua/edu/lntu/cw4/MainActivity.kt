package ua.edu.lntu.cw4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ32CR4Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var selectedItem by remember { mutableStateOf<String?>(null) }
    val items = (1..10).map { "Item $it" }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Screen 1", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        items.forEach { item ->
            Text(
                text = item,
                modifier = Modifier.clickable {
                    selectedItem = item
                }
            )
        }
        if (selectedItem != null) {
            Screen2(selectedItem!!)
        }
    }
}

@Composable
fun Screen2(item: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Screen 2", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Item Number: $item")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IPZ32CR4Theme {
        MyApp()
    }
}