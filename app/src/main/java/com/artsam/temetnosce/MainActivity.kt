package com.artsam.temetnosce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artsam.temetnosce.ui.sadhana.SadhanaScreen
import com.artsam.temetnosce.ui.theme.TemetNosceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemetNosceTheme {
                // A surface container using the 'background' color from the theme
                NavHost(
                    navController = rememberNavController(),
                    startDestination = "sadhana_screen"
                ) {
                    composable("sadhana_screen") {
                        SadhanaScreen()
                    }
                }
            }
        }
    }
}
