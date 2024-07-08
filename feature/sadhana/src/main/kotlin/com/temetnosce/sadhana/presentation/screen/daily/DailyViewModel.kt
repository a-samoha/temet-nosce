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
import com.temetnosce.sadhana.presentation.screen.daily.DailyIntent.ConfirmClick
import com.temetnosce.sadhana.presentation.screen.daily.DailyIntent.SadhanaItemValueChange
import com.temetnosce.sadhana.presentation.screen.daily.DailyIntent.ShowTimePicker
import com.temetnosce.sadhana.presentation.viewmodel.EmptyEffect
import com.temetnosce.sadhana.presentation.viewmodel.MviIntent
import com.temetnosce.sadhana.presentation.viewmodel.MviState
import com.temetnosce.sadhana.presentation.viewmodel.MviViewModel
import kotlinx.coroutines.launch

class DailyMviViewModel(
    private val getDailySadhanaUseCase: GetDailySadhanaUseCase,
    private val saveDailySadhanaUseCase: InsertDailySadhanaUseCase,
) : MviViewModel<DailyState, DailyIntent, EmptyEffect>() {

    override val initialState = DailyState()

    init {
        viewModelScope.launch {
            getDailySadhanaUseCase()
                .onFailure {}
                .onSuccess {
                    emitState(
                        DailyState(content = it.toSadhanaItemsList())
                    )
                }
        }
    }

    override fun processIntent(intent: DailyIntent) = when (intent) {
        is ShowTimePicker -> showTimePicker(intent)
        is SadhanaItemValueChange -> onSadhanaItemValueChange(intent)
        ConfirmClick -> onConfirmClick()
    }

    private fun showTimePicker(intent: ShowTimePicker) = emitState(
        currentState.copy(showTimePicker = intent.sadhanaItemId to intent.isVisible)
    )

    private fun onSadhanaItemValueChange(intent: SadhanaItemValueChange) = emitState(
        currentState.copy(content = currentState.content.map { item ->
            if (item.id == intent.sadhanaItemId) item.copy(value = intent.value)
            else item
        })
    )

    private fun onConfirmClick() {
        viewModelScope.launch {
            val data = (currentState as? DailyState)?.content?.toDailyModel()
                ?: DailyModel.EMPTY
            saveDailySadhanaUseCase(data)
                .onFailure { }
                .onSuccess { }
        }
    }
}

@Immutable
data class DailyState(
    val isLoading: Boolean = false,
    val content: List<SadhanaItemModel> = DailyModel.EMPTY.toSadhanaItemsList(),
    val showTimePicker: Pair<SadhanaItemId, Boolean> = MORNING_RISE to false,
) : MviState

@Immutable
sealed interface DailyIntent : MviIntent {

    data class ShowTimePicker(
        val sadhanaItemId: SadhanaItemId,
        val isVisible: Boolean
    ) : DailyIntent

    data class SadhanaItemValueChange(
        val sadhanaItemId: SadhanaItemId,
        val value: Any
    ) : DailyIntent

    data object ConfirmClick : DailyIntent
}