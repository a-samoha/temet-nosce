package com.artsam.sadhana.presentation.screen.daily

import com.artsam.sadhana.presentation.core.ui.MviState

sealed interface DailyState : MviState {

    object Uninitialized : DailyState
}