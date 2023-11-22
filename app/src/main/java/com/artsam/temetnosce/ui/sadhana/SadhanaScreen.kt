package com.artsam.temetnosce.ui.sadhana

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artsam.temetnosce.R
import com.artsam.temetnosce.domain.model.SadhanaItemModel

@Composable
fun SadhanaScreen() {
    // List of typical elements
    val elements = listOf(
        SadhanaItemModel("awake", "Подъём", R.drawable.ic_sun, null),
        SadhanaItemModel("service", "Служение", R.drawable.ic_feet, null),
        SadhanaItemModel("kirtan", "Киртан", R.drawable.ic_musicalnote, null),
        SadhanaItemModel("books", "Книги, мин", R.drawable.ic_bookopen, null),
        SadhanaItemModel("lectures", "Лекции", R.drawable.ic_headphones1, null),
        SadhanaItemModel("sleep", "Сон", R.drawable.ic_moon, null),
        SadhanaItemModel("japa73", "Джапа", null, R.string.time_0730),
        SadhanaItemModel("japa10", "", null, R.string.time_1000),
        SadhanaItemModel("japa18", "", null, R.string.time_1800),
        SadhanaItemModel("japa24", "", null, R.string.time_2400),
        // Add as many elements as needed
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Header Text
        Text(
            text = stringResource(R.string.my_sadhana_today),
            fontWeight = FontWeight.Bold,
            color = Color(R.color.black),
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 30.sp)
        )

        LazyColumn {
            items(elements) { item ->
                // For each item in the list, create a horizontal composition
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // "Label"
                    Text(
                        text = item.label,
                        modifier = Modifier.weight(1f)
                    )

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
                        val text = stringResource(id = it)
                        Text(
                            text = text,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    // "EditText" with border and rounded corners
                    BasicTextField(
                        value = "", // Here can be the state of your text
                        onValueChange = {},
                        modifier = Modifier
                            .weight(1f)
                            .border(
                                1.dp,
                                Color.Gray,
                                shape = RoundedCornerShape(8.dp)
                            ) // 1 dp thickness, gray color, 8 dp rounded corners
                            .padding(4.dp) // Additional padding for aesthetics
                    )
                }
            }
        }
    }
}