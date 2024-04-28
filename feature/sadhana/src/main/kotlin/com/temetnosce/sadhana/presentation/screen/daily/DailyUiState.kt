package com.temetnosce.sadhana.presentation.screen.daily

import com.temetnosce.sadhana.domain.model.DailyModel
import com.temetnosce.sadhana.presentation.core.ui.UiState

sealed interface DailyUiState : UiState {

    val bottomSheet: Sheet

    object Uninitialized : DailyUiState {
        override val bottomSheet = Sheet.None
    }

    data class Content(
        override val bottomSheet: Sheet = Sheet.None,
        val content: DailyModel
    ) : DailyUiState

    fun withSheet(value: Sheet): DailyUiState = when (this) {
        is Content -> copy(bottomSheet = value)
        else -> this
    }

    sealed interface Sheet {
        object None : Sheet
        object SelectLanguage : Sheet
    }
}