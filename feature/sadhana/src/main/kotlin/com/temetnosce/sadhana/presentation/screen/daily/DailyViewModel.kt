package com.temetnosce.sadhana.presentation.screen.daily

import androidx.lifecycle.viewModelScope
import com.temetnosce.sadhana.domain.model.SadhanaItemId
import com.temetnosce.sadhana.domain.model.SadhanaItemId.MORNING_RISE
import com.temetnosce.sadhana.domain.model.SadhanaItemModel
import com.temetnosce.sadhana.domain.usecase.GetDailySadhanaUseCase
import com.temetnosce.sadhana.domain.usecase.InsertDailySadhanaUseCase
import com.temetnosce.sadhana.presentation.core.ui.EmptyEffect
import com.temetnosce.sadhana.presentation.core.ui.UiState
import com.temetnosce.sadhana.presentation.core.viewmodel.MviViewModel
import kotlinx.coroutines.launch

class DailyViewModel(
    private val getDailySadhanaUseCase: GetDailySadhanaUseCase,
    private val saveDailySadhanaUseCase: InsertDailySadhanaUseCase,
) : MviViewModel<DailyUiState, EmptyEffect>() {

    override val initialState = DailyUiState.Uninitialized

    init {
        viewModelScope.launch {
            getDailySadhanaUseCase()
                .onFailure { }
                .onSuccess {
                    emitState(
                        DailyUiState.Content(
                            content = it.toSadhanaItemsList()
                        )
                    )
                }
        }
    }

    fun showTimePicker(sadhanaItemId: SadhanaItemId, isVisible: Boolean) {
        val currentState = (currentState as? DailyUiState.Content)
        currentState?.copy(showTimePicker = sadhanaItemId to isVisible)?.let(::emitState)
    }

    fun onSadhanaItemValueChange(value: Pair<SadhanaItemId, Any>) {
        val currentState = (currentState as? DailyUiState.Content)
        currentState?.copy(content = currentState.content.map {
            if (it.id == value.first) it.copy(value = value.second)
            else it
        })?.let(::emitState)
    }
}

sealed interface DailyUiState : UiState {

    data object Uninitialized : DailyUiState

    data class Content(
        val content: List<SadhanaItemModel>,
        val showTimePicker: Pair<SadhanaItemId, Boolean> = MORNING_RISE to false,
    ) : DailyUiState
}