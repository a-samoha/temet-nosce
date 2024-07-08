package com.temetnosce.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.temetnosce.presentation.navigation.graph.Dest
import com.temetnosce.presentation.navigation.graph.mainNavGraph
import com.temetnosce.presentation.theme.TemetNosceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemetNosceAppTheme {
                // A surface container using the 'background' color from the theme
                NavHost(
                    navController = rememberNavController(),
                    startDestination = Dest.ROUTE_DAILY_SADHANA,
                    builder = { mainNavGraph() }
                )
            }
        }
    }
}
