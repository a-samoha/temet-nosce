package com.temetnosce.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.get
import com.temetnosce.sadhana.presentation.screen.daily.DailyScreen

fun NavGraphBuilder.mainNavGraph() {
    addDestination(
        ComposeNavigator.Destination(provider[ComposeNavigator::class]) {
            DailyScreen()
        }.apply {
            route = Dest.ROUTE_DAILY_SADHANA
        }
    )

}

object Dest {
    const val ROUTE_DAILY_SADHANA = "DailySadhana"
}
