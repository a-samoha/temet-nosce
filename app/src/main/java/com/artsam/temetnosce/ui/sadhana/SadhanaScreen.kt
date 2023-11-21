package com.artsam.temetnosce.ui.sadhana

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.artsam.temetnosce.R
import com.artsam.temetnosce.domain.model.SadhanaItemModel

@Composable
fun SadhanaScreen() {
    // List of typical elements
    val elements = listOf(
        SadhanaItemModel("awake", "Подъём", R.drawable.ic_sun),
        SadhanaItemModel("service", "Служение", R.drawable.ic_feet),
        SadhanaItemModel("kirtan", "Киртан", R.drawable.ic_musicalnote),
        SadhanaItemModel("books", "Книги, мин", R.drawable.ic_bookopen),
        SadhanaItemModel("lectures", "Лекции", R.drawable.ic_sun),
        SadhanaItemModel("sleep", "Сон", R.drawable.ic_sun),
        SadhanaItemModel("japa73", "Джапа    07:30", R.drawable.ic_sun),
        SadhanaItemModel("japa10", "Джапа    10:00", R.drawable.ic_sun),
        SadhanaItemModel("japa18", "Джапа    18:00", R.drawable.ic_sun),
        SadhanaItemModel("japa24", "Джапа    24:00", R.drawable.ic_sun),
        // Add as many elements as needed
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

                // "Icon"
                Icon(
                    painter = painterResource(id = item.iconResId),
                    contentDescription = null, // setContentDescription can be added for accessibility
                    modifier = Modifier.padding(8.dp)
                )

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
