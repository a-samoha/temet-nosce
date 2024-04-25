package com.temetnosce.sadhana.presentation.screen.daily

import androidx.lifecycle.viewModelScope
import com.temetnosce.sadhana.domain.model.DailyModel
import com.temetnosce.sadhana.domain.usecase.DailySadhanaUpdateUseCase
import com.temetnosce.sadhana.presentation.core.ui.EmptyEffect
import com.temetnosce.sadhana.presentation.core.viewmodel.MviViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DailyViewModel(
    private val dailySadhanaUpdateUseCase: DailySadhanaUpdateUseCase,
) : MviViewModel<DailyState, EmptyEffect>() {

    override val emptyState = DailyState.Uninitialized

    init {
        viewModelScope.launch {
            delay(1000)
            updateState(
                DailyState.Content(
                    bottomSheet = DailyState.Sheet.None,
                    content = DailyModel.EMPTY
                )
            )
        }
    }

    fun onBooksChanged(value: Short) {
        val currentState = (currentState as? DailyState.Content)
        currentState?.copy(content = currentState.content.copy(books = value))
            ?.also { viewModelScope.launch { dailySadhanaUpdateUseCase(it.content) } }
            ?.let(::updateState)
    }
}