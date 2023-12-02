package com.artsam.temetnosce.core.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artsam.temetnosce.core.presentation.theme.TemetNosceAppTheme
import com.artsam.temetnosce.feature.sadhana.presentation.screen.SadhanaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemetNosceAppTheme {
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
