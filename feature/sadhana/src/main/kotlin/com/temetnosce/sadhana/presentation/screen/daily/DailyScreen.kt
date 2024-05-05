package com.temetnosce.sadhana.presentation.screen.daily

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.temetnosce.sadhana.R
import com.temetnosce.sadhana.domain.model.DailyModel
import com.temetnosce.sadhana.domain.model.SadhanaItemId
import com.temetnosce.sadhana.domain.model.SadhanaItemModel
import com.temetnosce.sadhana.presentation.core.ui.EmptyEvent
import com.temetnosce.sadhana.presentation.core.ui.MviScreen

class DailyScreen(
    private val viewModel: DailyViewModel
) : MviScreen<DailyUiState, EmptyEvent>(viewModel) {

    @Composable
    override fun Content() {
        ScreenContent(
            viewModel.uiState.collectAsStateWithLifecycle().value,
            viewModel::onBooksChanged,
        )
    }
}

@Composable
fun ScreenContent(
    uiState: DailyUiState,
    onBooksChange: (Pair<SadhanaItemId, Any>) -> Unit = {}
) {
    when (uiState) {
        is DailyUiState.Uninitialized -> ShowLoading()
        is DailyUiState.Content -> {
            val elements = listOf(
                SadhanaItemModel(
                    SadhanaItemId.MORNING_RISE,
                    "Подъём",
                    R.drawable.sadhana_ic_sun,
                    value = 0
                ),
                SadhanaItemModel(
                    SadhanaItemId.KRSHNA_SERVICE,
                    "Служение",
                    R.drawable.sadhana_ic_feet,
                    value = 0
                ),
                SadhanaItemModel(
                    SadhanaItemId.KIRTAN,
                    "Киртан",
                    R.drawable.sadhana_ic_musicalnote,
                    value = 0
                ),
                SadhanaItemModel(
                    SadhanaItemId.BOOKS_MIN,
                    "Книги, мин",
                    R.drawable.sadhana_ic_bookopen,
                    value = 0
                ),
                SadhanaItemModel(
                    SadhanaItemId.LECTURES,
                    "Лекции",
                    R.drawable.sadhana_ic_headphones1,
                    value = 0
                ),
                SadhanaItemModel(
                    SadhanaItemId.LIGHTS_OUT,
                    "Сон",
                    R.drawable.sadhana_ic_moon,
                    value = 0
                ),
                SadhanaItemModel(
                    SadhanaItemId.JAPA_07,
                    "Джапа",
                    null,
                    R.string.sadhana_time_0730,
                    value = 0
                ),
                SadhanaItemModel(
                    SadhanaItemId.JAPA_10,
                    "",
                    null,
                    R.string.sadhana_time_1000,
                    value = 0
                ),
                SadhanaItemModel(
                    SadhanaItemId.JAPA_18,
                    "",
                    null,
                    R.string.sadhana_time_1800,
                    value = 0
                ),
                SadhanaItemModel(
                    SadhanaItemId.JAPA_24,
                    "",
                    null,
                    R.string.sadhana_time_2400,
                    value = 0
                ),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Title(R.string.sadhana_my_sadhana_today)
                LazyColumn {
                    items(elements) { item ->
                        SadhanaItem(item, onValueChange = onBooksChange)
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}

@Composable
fun ShowLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(80.dp),
            strokeWidth = 8.dp,
            color = colorResource(id = R.color.sadhana_primary_color),
        )
    }
}

@Composable
fun Title(titleRes: Int) {
    Text(
        text = stringResource(titleRes),
        fontWeight = FontWeight.Bold,
        color = colorResource(id = R.color.sadhana_black),
        modifier = Modifier
            .padding(top = 16.dp, bottom = 24.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.headlineLarge.copy(fontSize = 30.sp),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SadhanaItem(
    item: SadhanaItemModel,
    onValueChange: (Pair<SadhanaItemId, Any>) -> Unit,
) {
    // For each item in the list, create a horizontal composition
    Column {
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Label(item.label, Modifier.weight(1f))
            IconOrText(item)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 32.dp),
            ) {
                when (item.id) {
                    SadhanaItemId.MORNING_RISE -> TextField(
                        value = item.value.toString(), onValueChange = {},
                    )
                    SadhanaItemId.KRSHNA_SERVICE -> Checkbox(
                        checked = false, onCheckedChange = {},
                    )
                    SadhanaItemId.KIRTAN -> Checkbox(
                        checked = false, onCheckedChange = {},
                    )
                    SadhanaItemId.BOOKS_MIN -> TextField(
                        value = item.value.toString(),
                        onValueChange = { onValueChange(item.id to it) }, //if (it.isDigitsOnly()) { onValueChange(it.toShort()) }
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.background(color = Color.White),
                        textStyle = LocalTextStyle.current.copy(color = Color.Black), // Set the text color
                    )
                    SadhanaItemId.LECTURES -> Checkbox(
                        checked = false, onCheckedChange = {},
                    )
                    SadhanaItemId.LIGHTS_OUT -> TextField(
                        value = item.value.toString(), onValueChange = {},
                    )
                    SadhanaItemId.JAPA_07 -> TextField(
                        value = item.value.toString(), onValueChange = {},
                    )
                    SadhanaItemId.JAPA_10 -> TextField(
                        value = item.value.toString(), onValueChange = {},
                    )
                    SadhanaItemId.JAPA_18 -> TextField(
                        value = item.value.toString(), onValueChange = {},
                    )
                    SadhanaItemId.JAPA_24 -> TextField(
                        value = item.value.toString(), onValueChange = {},
                    )
                }
            }
        }
    }
}

@Composable
fun Label(label: String, modifier: Modifier) {
    Text(
        text = label,
        modifier = modifier,
    )
}

@Composable
fun IconOrText(item: SadhanaItemModel) {
    // "Icon" or "Text" based on the resource type
    item.iconResId?.let {
        // If it's an image resource
        Icon(
            painter = painterResource(id = it),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp)
        )
    }

    item.timeLabelResId?.let {
        // If it's a string resource
        Text(
            text = stringResource(id = it),
            modifier = Modifier.padding(0.dp)
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewScreenContent() {
    ScreenContent(
        uiState = DailyUiState.Content(
            content = DailyModel.EMPTY
        )
    )
}