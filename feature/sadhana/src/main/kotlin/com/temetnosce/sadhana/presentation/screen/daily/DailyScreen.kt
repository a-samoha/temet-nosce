package com.temetnosce.sadhana.presentation.screen.daily

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
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
import com.temetnosce.sadhana.domain.model.SadhanaItemId
import com.temetnosce.sadhana.domain.model.SadhanaItemModel
import com.temetnosce.sadhana.presentation.screen.daily.DailyIntent.ConfirmClick
import com.temetnosce.sadhana.presentation.screen.daily.DailyIntent.SadhanaItemValueChange
import com.temetnosce.sadhana.presentation.screen.daily.DailyIntent.ShowTimePicker
import com.temetnosce.sadhana.presentation.theme.SadhanaTheme
import com.temetnosce.sadhana.presentation.theme.SadhanaTypography
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Locale

private val SADHANA_ITEM_HEIGHT = 48.dp
private val SADHANA_ITEMS = 6
private val JAPA_ITEMS = 4

@Composable
fun DailyScreen() {
    val viewModel = koinViewModel<DailyMviViewModel>()
    SadhanaTheme {
        DailyScreenContent(
            viewModel.uiState.collectAsStateWithLifecycle().value,
            viewModel::processIntent,
        )
    }
}

@Composable
fun DailyScreenContent(
    uiState: DailyState,
    processIntent: (DailyIntent) -> Unit,
) {
    // region Loading
    if (uiState.isLoading) {
        ShowLoading()
    }
    // endregion

    // region TimePicker
    if (uiState.showTimePicker.second) {
        val sadhanaItemId = uiState.showTimePicker.first
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())

        TimePickerDialog(
            onCancel = { processIntent(ShowTimePicker(sadhanaItemId, false)) },
            onConfirm = { calendar ->
                processIntent(ShowTimePicker(sadhanaItemId, false))
                processIntent(
                    SadhanaItemValueChange(
                        sadhanaItemId,
                        formatter.format(calendar.time)
                    )
                )
            },
        )
    }
    // endregion

    // region Sadhana Items
    val items = uiState.content
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Title(R.string.sadhana_my_sadhana_today)
        Column {
            repeat(SADHANA_ITEMS) { i -> // list item position from 0
                SadhanaItem(
                    items[i],
                    processIntent = processIntent,
                )
            }
        }
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            repeat(JAPA_ITEMS) { i ->
                SadhanaItem(
                    items[SADHANA_ITEMS + i],
                    processIntent = processIntent,
                )
            }
        }
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = stringResource(R.string.sadhana_per_month),
                style = SadhanaTypography.headlineSmall,
                color = colorResource(id = R.color.sadhana_primary_color),
            )
            Text(
                modifier = Modifier.clickable { processIntent(ConfirmClick) },
                text = stringResource(R.string.sadhana_confirm),
                style = SadhanaTypography.headlineSmall,
                color = colorResource(id = R.color.sadhana_primary_color),
            )
        }
    }
    // endregion
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

@Composable
fun SadhanaItem(
    item: SadhanaItemModel,
    processIntent: (DailyIntent) -> Unit,
) {
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
                processIntent = processIntent,
                modifier = Modifier.weight(1f),
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
    processIntent: (DailyIntent) -> Unit,
    modifier: Modifier,
) = Box(
    contentAlignment = Alignment.Center,
    modifier = modifier,
) {
    when (item.id) {
        SadhanaItemId.KRSHNA_SERVICE,
        SadhanaItemId.KIRTAN,
        SadhanaItemId.LECTURES -> SadhanaCheckbox(
            checked = item.value == true,
            onCheckedChange = { processIntent(SadhanaItemValueChange(item.id, it)) },
        )

        else -> SadhanaTextField(
            value = item.value.toString(),
            onValueChange = { processIntent(SadhanaItemValueChange(item.id, it)) },
            enabled = when (item.id) {
                SadhanaItemId.MORNING_RISE,
                SadhanaItemId.LIGHTS_OUT -> false
                else -> true
            },
            modifier = when (item.id) {
                SadhanaItemId.MORNING_RISE,
                SadhanaItemId.LIGHTS_OUT -> Modifier.clickable {
                    processIntent(ShowTimePicker(item.id, true))
                }
                else -> Modifier
            },
            keyboardOptions = when (item.id) {
                SadhanaItemId.BOOKS_MIN,
                SadhanaItemId.JAPA_07,
                SadhanaItemId.JAPA_10,
                SadhanaItemId.JAPA_18,
                SadhanaItemId.JAPA_24 -> KeyboardOptions(keyboardType = KeyboardType.Number)
                else -> KeyboardOptions.Default
            },
            placeholderText = when (item.id) {
                SadhanaItemId.MORNING_RISE -> stringResource(id = R.string.sadhana_placeholder_04_00)
                SadhanaItemId.BOOKS_MIN -> stringResource(id = R.string.sadhana_placeholder_30)
                SadhanaItemId.LIGHTS_OUT -> stringResource(id = R.string.sadhana_placeholder_21_30)
                SadhanaItemId.JAPA_07 -> stringResource(id = R.string.sadhana_placeholder_16)
                else -> ""
            },
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
    onValueChange: (String) -> Unit, // clear focus https://stackoverflow.com/a/67058925
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholderText: String = "",
) = BasicTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    enabled = enabled,
    textStyle = TextStyle(
        color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.Black,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
    ),
    keyboardOptions = keyboardOptions,
    singleLine = true,
    decorationBox = { innerTextField ->
        Box(contentAlignment = Alignment.Center) {
            if (value.isBlank()) {
                Text(
                    text = placeholderText,
                    color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.LightGray,
                    fontSize = 14.sp
                )
            }
            innerTextField()
        }
    }
)

@Composable
@Preview(showSystemUi = true)
private fun PreviewScreenContent() {
    DailyScreenContent(
        uiState = DailyState(),
        processIntent = {},
    )
}