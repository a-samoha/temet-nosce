package com.temetnosce.sadhana.presentation.screen.daily

import com.temetnosce.sadhana.domain.model.DailyModel
import com.temetnosce.sadhana.presentation.core.ui.MviState

sealed interface DailyState : MviState {

    val bottomSheet: Sheet

    object Uninitialized : DailyState {
        override val bottomSheet = Sheet.None
    }

    data class Content(
        override val bottomSheet: Sheet = Sheet.None,
        val content: DailyModel
    ) : DailyState

    fun withSheet(value: Sheet): DailyState = when (this) {
        is Content -> copy(bottomSheet = value)
        else -> this
    }

    sealed interface Sheet {
        object None : Sheet
        object SelectLanguage : Sheet
    }
}