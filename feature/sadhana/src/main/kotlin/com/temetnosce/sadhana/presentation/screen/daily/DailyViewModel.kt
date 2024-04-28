package com.temetnosce.sadhana.presentation.screen.daily

import androidx.lifecycle.viewModelScope
import com.temetnosce.sadhana.domain.model.DailyModel
import com.temetnosce.sadhana.domain.usecase.DailySadhanaUpdateUseCase
import com.temetnosce.sadhana.presentation.core.ui.EmptyEvent
import com.temetnosce.sadhana.presentation.core.viewmodel.MviViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DailyViewModel(
    private val dailySadhanaUpdateUseCase: DailySadhanaUpdateUseCase,
) : MviViewModel<DailyUiState, EmptyEvent>() {

    override val emptyState = DailyUiState.Uninitialized

    init {
        viewModelScope.launch {
            delay(1000)
            updateState(
                DailyUiState.Content(
                    bottomSheet = DailyUiState.Sheet.None,
                    content = DailyModel.EMPTY
                )
            )
        }
    }

    fun onBooksChanged(value: Short) {
        val currentState = (currentState as? DailyUiState.Content)
        currentState?.copy(content = currentState.content.copy(books = value))
            ?.also { viewModelScope.launch { dailySadhanaUpdateUseCase(it.content) } }
            ?.let(::updateState)
    }
}