package com.artsam.temetnosce.feature.sadhana.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artsam.temetnosce.domain.model.SadhanaItemModel
import com.artsam.temetnosce.feature.sadhana.R

@Composable
fun SadhanaScreen() {
    // List of typical elements
    val elements = listOf(
        SadhanaItemModel("awake", "Подъём", R.drawable.sadhana_ic_sun),
        SadhanaItemModel("service", "Служение", R.drawable.sadhana_ic_feet),
        SadhanaItemModel("kirtan", "Киртан", R.drawable.sadhana_ic_musicalnote),
        SadhanaItemModel("books", "Книги, мин", R.drawable.sadhana_ic_bookopen),
        SadhanaItemModel("lectures", "Лекции", R.drawable.sadhana_ic_headphones1),
        SadhanaItemModel("sleep", "Сон", R.drawable.sadhana_ic_moon),
        SadhanaItemModel(
            "japa73",
            "Джапа",
            null,
            R.string.sadhana_time_0730,
            R.string.sadhana_japa_rounds_placeholder
        ),
        SadhanaItemModel("japa10", "", null, R.string.sadhana_time_1000),
        SadhanaItemModel("japa18", "", null, R.string.sadhana_time_1800),
        SadhanaItemModel("japa24", "", null, R.string.sadhana_time_2400),
        // Add as many elements as needed
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Title(R.string.sadhana_my_sadhana_today)
        LazyColumn {
            items(elements) { item ->
                Item(item)
            }
        }
    }
}

@Composable
fun Title(titleRes: Int) {
    // Header Text
    Text(
        text = stringResource(titleRes),
        fontWeight = FontWeight.Bold,
        color = Color(R.color.sadhana_black),
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.headlineLarge.copy(fontSize = 30.sp),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Item(item: SadhanaItemModel) {
    // For each item in the list, create a horizontal composition
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Label(item.label, Modifier.weight(1f))
        IconOrText(item)

        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            // "EditText" with border and rounded corners
            value = text, // Here can be the state of your text
            onValueChange = { text = it },
            singleLine = true,
            modifier = Modifier
                //.align(Alignment.CenterHorizontally)
                .weight(1f)
                .padding(horizontal = 32.dp),
            textStyle = LocalTextStyle.current.copy(color = Color.Black), // Set the text color
            shape = RoundedCornerShape(8.dp),
            //label = { Text("Label") },
            //visualTransformation = PasswordVisualTransformation(),
            placeholder = {
                item.placeholder?.let {
                    Text(
                        text = stringResource(id = it),
                        color = Color.Gray
                    )
                }
            },
        )
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

    item.timeResId?.let {
        // If it's a string resource
        Text(
            text = stringResource(id = it),
            modifier = Modifier.padding(0.dp)
        )
    }
}
