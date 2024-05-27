package com.temetnosce.sadhana.presentation.screen.daily

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
import com.temetnosce.sadhana.presentation.screen.daily.DailyScreen.Companion.JAPA_ITEMS
import com.temetnosce.sadhana.presentation.screen.daily.DailyScreen.Companion.SADHANA_ITEMS
import com.temetnosce.sadhana.presentation.screen.daily.DailyScreen.Companion.SADHANA_ITEM_HEIGHT
import com.temetnosce.sadhana.presentation.theme.SadhanaTheme
import com.temetnosce.sadhana.presentation.theme.SadhanaTypography

class DailyScreen(
    private val viewModel: DailyViewModel
) : MviScreen<DailyUiState, EmptyEvent>(viewModel) {

    @Composable
    override fun Content() {
        SadhanaTheme {
            ScreenContent(
                viewModel.uiState.collectAsStateWithLifecycle().value,
                viewModel::onBooksChanged,
            )
        }
    }

    companion object {
        val SADHANA_ITEM_HEIGHT = 48.dp
        val SADHANA_ITEMS = 6
        val JAPA_ITEMS = 4
    }
}

@Composable
fun ScreenContent(
    uiState: DailyUiState,
    onValueChange: (Pair<SadhanaItemId, Any>) -> Unit = {}
) {
    when (uiState) {
        is DailyUiState.Uninitialized -> ShowLoading()
        is DailyUiState.Content -> {
            val elements = uiState.content
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Title(R.string.sadhana_my_sadhana_today)
                Column {
                    repeat(SADHANA_ITEMS) { item ->
                        SadhanaItem(elements[item], onValueChange = onValueChange)
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    repeat(JAPA_ITEMS) { item ->
                        SadhanaItem(elements[SADHANA_ITEMS + item], onValueChange = onValueChange)
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
        style = SadhanaTypography.titleLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(vertical = 24.dp)
            .fillMaxWidth(),
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
                .height(SADHANA_ITEM_HEIGHT)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Label(item.label?.let { stringResource(id = it) } ?: "", Modifier.weight(2f))
            IconOrText(item, Modifier.width(64.dp))
            VerticalDivider()
            ValueContainer(
                item = item,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f)
            )
            VerticalDivider(modifier = Modifier.padding(end = 24.dp))
        }
    }
}

@Composable
fun Label(label: String, modifier: Modifier) {
    Text(
        text = label,
        style = SadhanaTypography.bodyLarge,
        modifier = modifier,
    )
}

/**
 * Box with icon/time_text based on the resource type
 */
@Composable
fun IconOrText(
    item: SadhanaItemModel,
    modifier: Modifier,
) {
    when {
        item.iconResId != null -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
            ) {
                Icon(
                    modifier = Modifier.padding(end = 12.dp),
                    painter = painterResource(id = item.iconResId),
                    contentDescription = null,
                )
            }
        }
        item.timeLabelResId != null -> {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
            ) {
                Text(
                    text = stringResource(id = item.timeLabelResId),
                    style = SadhanaTypography.labelLarge
                )
                Spacer(modifier = Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .width(6.dp)
                        .fillMaxHeight()
                        .background(
                            colorResource(
                                id = when (item.id) {
                                    SadhanaItemId.JAPA_07 -> R.color.sadhana_japa_07
                                    SadhanaItemId.JAPA_10 -> R.color.sadhana_japa_10
                                    SadhanaItemId.JAPA_18 -> R.color.sadhana_japa_18
                                    SadhanaItemId.JAPA_24 -> R.color.sadhana_japa_24
                                    else -> R.color.sadhana_white
                                }
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun ValueContainer(
    item: SadhanaItemModel,
    onValueChange: (Pair<SadhanaItemId, Any>) -> Unit,
    modifier: Modifier,
) = Box(
    contentAlignment = Alignment.Center,
    modifier = modifier,
) {
    when (item.id) {
        SadhanaItemId.MORNING_RISE -> SadhanaTextField(
            value = item.value.toString(),
            onValueChange = { onValueChange(item.id to it) },
            placeholderText = "04:00",
        )
        SadhanaItemId.KRSHNA_SERVICE -> SadhanaCheckbox(
            checked = item.value == true, onCheckedChange = { onValueChange(item.id to it) },
        )
        SadhanaItemId.KIRTAN -> SadhanaCheckbox(
            checked = item.value == true, onCheckedChange = { onValueChange(item.id to it) },
        )
        SadhanaItemId.BOOKS_MIN -> SadhanaTextField(
            value = item.value.toString(),
            onValueChange = { onValueChange(item.id to it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholderText = "30",
        )
        SadhanaItemId.LECTURES -> SadhanaCheckbox(
            checked = item.value == true, onCheckedChange = { onValueChange(item.id to it) },
        )
        SadhanaItemId.LIGHTS_OUT -> SadhanaTextField(
            value = item.value.toString(),
            onValueChange = { onValueChange(item.id to it) },
            placeholderText = "21:30",
        )
        SadhanaItemId.JAPA_07 -> SadhanaTextField(
            value = item.value.toString(),
            onValueChange = { onValueChange(item.id to it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholderText = "16",
        )
        SadhanaItemId.JAPA_10,
        SadhanaItemId.JAPA_18,
        SadhanaItemId.JAPA_24 -> SadhanaTextField(
            value = item.value.toString(),
            onValueChange = { onValueChange(item.id to it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}

@Composable
private fun SadhanaCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) = Checkbox(
    checked,
    onCheckedChange,
    modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.sadhana_checkbox_bg))
)

@Composable
private fun SadhanaTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholderText: String = "",
) = BasicTextField(
    value = value,
    onValueChange = onValueChange,
    textStyle = TextStyle(
        color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.Black, //colorResource(id = R.color.sadhana_primary_text_colo)
        textAlign = TextAlign.Center,
    ),
    keyboardOptions = keyboardOptions,
    singleLine = true,
    decorationBox = { innerTextField ->
        Box {
            if (value.isBlank()) {
                Text(
                    text = placeholderText,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(),
                    color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.LightGray,
                    fontSize = 14.sp
                )
            }
        }
        innerTextField()
    }
)

@Composable
@Preview(showSystemUi = true)
private fun PreviewScreenContent() {
    ScreenContent(
        uiState = DailyUiState.Content(
            content = DailyModel.EMPTY.toSadhanaItemsList()
        )
    )
}