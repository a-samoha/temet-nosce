package com.temetnosce.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.temetnosce.sadhana.presentation.screen.daily.DailyScreen
import com.temetnosce.presentation.theme.TemetNosceAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemetNosceAppTheme {
                // A surface container using the 'background' color from the theme
                NavHost(
                    navController = rememberNavController(),
                    startDestination = "sadhana_feature"
                ) {
                    composable("sadhana_feature") {
                        DailyScreen(viewModel = koinViewModel()).Bind()
                    }
                }
            }
        }
    }
}
