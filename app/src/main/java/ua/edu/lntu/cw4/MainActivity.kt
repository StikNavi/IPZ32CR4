import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    var selectedItem by remember { mutableStateOf<ImageInfo?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                    )
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentStep) {
                1 -> Screen1(
                    onItemClick = { selectedItem = it; currentStep = 2 },
                    modifier = Modifier.fillMaxSize()
                )
                2 -> Screen2(
                    selectedItem = selectedItem,
                    onBackClick = { currentStep = 1 },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun Screen1(
    onItemClick: (ImageInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageList = listOf(
        ImageInfo(R.drawable.study, "Description for image 1", "Additional info for image 1"),
        ImageInfo(R.drawable.study, "Description for image 2", "Additional info for image 2"),
        ImageInfo(R.drawable.study, "Description for image 3", "Additional info for image 3"),
        ImageInfo(R.drawable.study, "Description for image 4", "Additional info for image 4"),
        ImageInfo(R.drawable.study, "Description for image 5", "Additional info for image 5")
    )

    LazyColumn(
        modifier = modifier
    ) {
        items(imageList) { imageInfo ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onItemClick(imageInfo) }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = imageInfo.imageResId),
                        contentDescription = "Image ${imageList.indexOf(imageInfo) + 1}",
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Image ${imageList.indexOf(imageInfo) + 1}")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = imageInfo.description)
            }
        }
    }
}

@Composable
fun Screen2(selectedItem: ImageInfo?, onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = "Description for selected image",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = selectedItem?.description ?: "",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = onBackClick,
            colors = ButtonDefaults.run { buttonColors(Color.Red) }
        ) {
            Text(text = "Back")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = selectedItem?.additionalInfo ?: "",
        )
    }
}

data class ImageInfo(val imageResId: Int, val description: String, val additionalInfo: String)

@Preview
@Composable
fun MedicinePreview() {
    IPZ32CR4Theme {
        MedicineApp()
    }
}
