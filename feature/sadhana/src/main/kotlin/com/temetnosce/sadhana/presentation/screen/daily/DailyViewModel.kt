package com.temetnosce.sadhana.presentation.screen.daily

import androidx.lifecycle.viewModelScope
import com.temetnosce.sadhana.domain.model.SadhanaItemId
import com.temetnosce.sadhana.domain.model.SadhanaItemModel
import com.temetnosce.sadhana.domain.usecase.GetDailySadhanaUseCase
import com.temetnosce.sadhana.domain.usecase.InsertDailySadhanaUseCase
import com.temetnosce.sadhana.presentation.core.ui.EmptyEvent
import com.temetnosce.sadhana.presentation.core.ui.UiState
import com.temetnosce.sadhana.presentation.core.viewmodel.MviViewModel
import kotlinx.coroutines.launch

class DailyViewModel(
    private val getDailySadhanaUseCase: GetDailySadhanaUseCase,
    private val saveDailySadhanaUseCase: InsertDailySadhanaUseCase,
) : MviViewModel<DailyUiState, EmptyEvent>() {

    override val emptyState = DailyUiState.Uninitialized

    init {
        viewModelScope.launch {
            getDailySadhanaUseCase()
                .onFailure { }
                .onSuccess {
                    updateState(
                        DailyUiState.Content(
                            bottomSheet = DailyUiState.Sheet.None,
                            content = it.toSadhanaItemsList()
                        )
                    )
                }
        }
    }

    fun onBooksChanged(value: Pair<SadhanaItemId, Any>) {
        val currentState = (currentState as? DailyUiState.Content)
//        currentState?.copy(content = currentState.content.copy(books = value))
//            ?.also { viewModelScope.launch { saveDailySadhanaUseCase(it.content) } }
//            ?.let(::updateState)
    }
}

sealed interface DailyUiState : UiState {

    val bottomSheet: Sheet

    object Uninitialized : DailyUiState {
        override val bottomSheet = Sheet.None
    }

    data class Content(
        override val bottomSheet: Sheet = Sheet.None,
        val content: List<SadhanaItemModel>
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