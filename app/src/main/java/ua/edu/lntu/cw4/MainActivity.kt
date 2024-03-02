package com.example.uaedulntucw4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edu.lntu.cw4.R
import ua.edu.lntu.cw4.ui.theme.IPZ32CR4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ32CR4Theme {
                MedicineApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineApp() {
    var currentStep by remember { mutableStateOf(1) }
    var selectedItem by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(
                        onClick = { /* Handle action */ },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = "Settings"
                            )
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentStep) {
                1 -> Screen1(
                    onItemClick = { selectedItem = it + 1; currentStep = 2 },
                    modifier = Modifier.fillMaxSize()
                )
                2 -> Screen2(
                    selectedItemId = selectedItem,
                    onBackClick = { currentStep = 1 },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun Screen1(onItemClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    val list = (1..10).toList() // Довільний список елементів
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(list) { index, item ->
            ListItem(
                text = { Text("Item $item") },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Settings"
                    )
                },
                modifier = Modifier.clickable { onItemClick(index) }
            )
        }
    }
}

@Composable
fun Screen2(selectedItemId: Int?, onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Selected Item: ${selectedItemId ?: "None"}",
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onBackClick,
                colors = ButtonDefaults.run { buttonColors(Color.Red) }
            ) {
                Text(text = "Back")
            }
        }
    }
}

@Preview
@Composable
fun MedicinePreview() {
    IPZ32CR4Theme {
        MedicineApp()
    }
}
