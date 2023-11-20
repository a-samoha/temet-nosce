package com.artsam.temetnosce.ui.sadhana

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.artsam.temetnosce.domain.model.SadhanaItemModel

@Composable
fun SadhanaScreen() {
    // List of typical elements
    val elements = listOf(
        SadhanaItemModel("awake","Подъём", Icons.Default.AccountCircle),
        SadhanaItemModel("service","Служение", Icons.Default.Email),
        SadhanaItemModel("kirtan","Киртан", Icons.Default.Phone),
        SadhanaItemModel("books","Книги, мин", Icons.Default.Phone),
        SadhanaItemModel("lectures","Лекции", Icons.Default.Phone),
        SadhanaItemModel("sleep","Сон", Icons.Default.Phone),
        SadhanaItemModel("japa73","Джапа    07:30", Icons.Default.Phone),
        SadhanaItemModel("japa10","Джапа    10:00", Icons.Default.Phone),
        SadhanaItemModel("japa18","Джапа    18:00", Icons.Default.Phone),
        SadhanaItemModel("japa24","Джапа    24:00", Icons.Default.Phone),
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
                    imageVector = item.icon,
                    contentDescription = null, // setContentDescription can be added for accessibility
                    modifier = Modifier.padding(8.dp)
                )

                // "EditText" with border and rounded corners
                BasicTextField(
                    value = "", // Here can be the state of your text
                    onValueChange = {},
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp)) // 1 dp thickness, gray color, 8 dp rounded corners
                        .padding(4.dp) // Additional padding for aesthetics
                )
            }
        }
    }
}
