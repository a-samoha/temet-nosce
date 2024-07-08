package com.temetnosce.sadhana.presentation.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.temetnosce.sadhana.presentation.core.viewmodel.ViewModel

@Composable
fun <S : UiState, E : UiEvent> Screen(
    viewModel: ViewModel<S, E>,
    content: @Composable (state: S) -> Unit,
    launchEvent: @Composable (event: UiEvent) -> Unit = {},
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    content(state)

    val event by viewModel.eventsStream.collectAsStateWithLifecycle(initialValue = EmptyEvent)
    launchEvent(event)
}