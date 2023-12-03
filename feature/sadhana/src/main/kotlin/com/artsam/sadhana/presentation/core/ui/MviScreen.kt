//@file:Suppress("MemberVisibilityCanBePrivate")

package com.artsam.sadhana.presentation.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.artsam.sadhana.presentation.core.viewmodel.MviViewModel

abstract class MviScreen<S: MviState, E: MviEffect>(
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
