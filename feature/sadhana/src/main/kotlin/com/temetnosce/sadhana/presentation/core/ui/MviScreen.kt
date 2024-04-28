//@file:Suppress("MemberVisibilityCanBePrivate")

package com.temetnosce.sadhana.presentation.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.temetnosce.sadhana.presentation.core.viewmodel.MviViewModel

abstract class MviScreen<S: UiState, E: UiEvent>(
    private val viewModel: MviViewModel<S, E>,
) : Screen() {

    @Composable
    override fun Bind() {
        LaunchedEffect(KEY_EFFECTS_PROCESSOR) {
            viewModel.effectStream.collect(::processEffect)
        }
        super.Bind()
    }

    protected open fun processEffect(effect: E) = Unit

    companion object {
        private const val KEY_EFFECTS_PROCESSOR = "key_effects_processor"
    }
}
