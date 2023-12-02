package com.artsam.sadhana.presentation.screen.daily

import com.artsam.sadhana.presentation.core.ui.EmptyEffect
import com.artsam.sadhana.presentation.core.viewmodel.MviViewModel

class DailyViewModel() : MviViewModel<DailyState, EmptyEffect>() {

    override val emptyState = DailyState.Uninitialized
}