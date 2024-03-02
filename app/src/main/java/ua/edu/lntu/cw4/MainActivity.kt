package ua.edu.lntu.cw4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.saveable.rememberSaveable
import ua.edu.lntu.cw4.ui.theme.IPZ32CR4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ32CR4Theme {
                Surface(color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") { Screen1(navController = navController) }
        composable("screen2/{itemNumber}") { backStackEntry ->
            val itemNumber = backStackEntry.arguments?.getString("itemNumber") ?: ""
            Screen2(itemNumber = itemNumber)
        }
    }
}

@Composable
fun Screen1(navController: NavHostController) {
    val items = remember { (1..10).toList() }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Screen 1", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        items.forEach { item ->
            Text(
                text = "Item $item",
                modifier = Modifier.clickable {
                    navController.navigate("screen2/$item")
                }
            )
        }
    }
}

@Composable
fun Screen2(itemNumber: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Screen 2", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Item Number: $itemNumber")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IPZ32CR4Theme {
        MyApp()
    }
}
