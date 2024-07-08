package com.temetnosce.sadhana.presentation.screen.daily

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.temetnosce.sadhana.domain.model.DailyModel
import com.temetnosce.sadhana.domain.model.SadhanaItemId
import com.temetnosce.sadhana.domain.model.SadhanaItemId.MORNING_RISE
import com.temetnosce.sadhana.domain.model.SadhanaItemModel
import com.temetnosce.sadhana.domain.model.toDailyModel
import com.temetnosce.sadhana.domain.usecase.GetDailySadhanaUseCase
import com.temetnosce.sadhana.domain.usecase.InsertDailySadhanaUseCase
import com.temetnosce.sadhana.presentation.core.ui.UiEvent
import com.temetnosce.sadhana.presentation.core.ui.UiState
import com.temetnosce.sadhana.presentation.core.viewmodel.ViewModel
import kotlinx.coroutines.launch

class DailyViewModel(
    private val getDailySadhanaUseCase: GetDailySadhanaUseCase,
    private val saveDailySadhanaUseCase: InsertDailySadhanaUseCase,
) : ViewModel<DailyUiState, DailyUiEvent>() {

    override val initialState = DailyUiState()

    init {
        viewModelScope.launch {
            getDailySadhanaUseCase()
                .onFailure {}
                .onSuccess {
                    emitState(
                        DailyUiState(content = it.toSadhanaItemsList())
                    )
                }
        }
    }

    fun showTimePicker(sadhanaItemId: SadhanaItemId, isVisible: Boolean) {
        val currentState = (currentState as? DailyUiState)
        currentState?.copy(showTimePicker = sadhanaItemId to isVisible)?.let(::emitState)
    }

    fun onSadhanaItemValueChange(value: Pair<SadhanaItemId, Any>) {
        val currentState = (currentState as? DailyUiState)
        currentState?.copy(content = currentState.content.map {
            if (it.id == value.first) it.copy(value = value.second)
            else it
        })?.let(::emitState)
    }

    fun onConfirmClick() {
        viewModelScope.launch {
            val data = (currentState as? DailyUiState)?.content?.toDailyModel()
                ?: DailyModel.EMPTY
            saveDailySadhanaUseCase(data)
                .onFailure { }
                .onSuccess { }
        }
    }
}

@Immutable
data class DailyUiState(
    val isLoading: Boolean = false,
    val content: List<SadhanaItemModel> = DailyModel.EMPTY.toSadhanaItemsList(),
    val showTimePicker: Pair<SadhanaItemId, Boolean> = MORNING_RISE to false,
) : UiState

sealed interface DailyUiEvent : UiEvent {

    data class Loading(
        val isLoading: Boolean = false
    ) : DailyUiEvent

    data class ShowTimePicker(
        val itemId: SadhanaItemId,
        val isEnabled: Boolean = false
    ) : DailyUiEvent
}
