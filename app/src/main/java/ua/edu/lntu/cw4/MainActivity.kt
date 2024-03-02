package ua.edu.lntu.cw4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
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
    var selectedItem by remember { mutableStateOf<Int?>(null) }
    var selectedImageDesc by remember { mutableStateOf("") }
    var selectedImageIndex by remember { mutableStateOf<Int?>(null) }

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
                    onItemClick = { selectedItem = it + 1; selectedImageIndex = it; currentStep = 2 },
                    onDescClick = { selectedImageDesc = it },
                    modifier = Modifier.fillMaxSize()
                )
                2 -> Screen2(
                    selectedImageDesc = selectedImageDesc,
                    selectedImageIndex = selectedImageIndex,
                    onBackClick = { currentStep = 1 },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun Screen1(
    onItemClick: (Int) -> Unit,
    onDescClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageList = listOf(
        ImageInfo(R.drawable.pills, "Ліки за рецептом", "Препарати призначаються ліцензованим рецептом для конкретної особи використання і регулюється через Управління з контролю за продуктами і ліками Сполучених Штатів (FDA)."),
        ImageInfo(R.drawable.pills, "Загальні ліки", "Загальні препарати можуть бути безпечними та ефективними альтернативами своїм фірмовим аналогам і часто за зниженою вартістю."),
        ImageInfo(R.drawable.pills, "Безрецептурні ліки", "Безрецептурні (безрецептурні) ліки не вимагають рецепта."),
        ImageInfo(R.drawable.pills, "Трав'яні препарати та добавки", "Трави та добавки можуть включати в себе широкий спектр речовин, включаючи вітаміни, мінерали, ферменти, і рослинні речовини."),
    )

    LazyColumn(
        modifier = modifier
    ) {
        items(imageList) { imageInfo ->
            var showMoreInfo by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onItemClick(imageList.indexOf(imageInfo)) }
                ) {
                    Image(
                        painter = painterResource(id = imageInfo.imageResId),
                        contentDescription = " ${imageList.indexOf(imageInfo)}",
                        modifier = Modifier.size(50.dp)
                    )

                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = imageInfo.description,
                    modifier = Modifier.clickable { onDescClick(imageInfo.description) }
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (showMoreInfo) {
                    Text(
                        text = imageInfo.moreInfo,
                    )
                } else {
                    Button(
                        onClick = { showMoreInfo = !showMoreInfo },
                        colors = ButtonDefaults.run { buttonColors(Color.Gray) }
                    ) {
                        Text("More Info")
                    }
                }
            }
        }
    }
}

@Composable
fun Screen2(selectedImageDesc: String?, selectedImageIndex: Int?, onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    val textToShow = when (selectedImageIndex) {
        0 -> "Успішна покупка 1"
        1 -> "Успішна покупка 2"
        2 -> "Успішна покупка 3"
        3 -> "Успішна покупка 4"
        else -> ""
    }

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = textToShow,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = selectedImageDesc ?: "",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = onBackClick,
            colors = ButtonDefaults.run { buttonColors(Color.Red) }
        ) {
            Text(text = "Назад")
        }
    }
}

data class ImageInfo(val imageResId: Int, val description: String, val moreInfo: String)

@Preview
@Composable
fun MedicinePreview() {
    IPZ32CR4Theme {
        MedicineApp()
    }
}
