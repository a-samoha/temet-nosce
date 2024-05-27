@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.temetnosce.sadhana.presentation.core.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.temetnosce.sadhana.BuildConfig
import com.temetnosce.sadhana.presentation.core.ui.UiEvent
import com.temetnosce.sadhana.presentation.core.ui.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MviViewModel<S : UiState, E : UiEvent> : ViewModel() {

    abstract val emptyState: S
    private val _uiState: MutableStateFlow<S> by lazy { MutableStateFlow(emptyState) }
    val uiState: StateFlow<S> by lazy { _uiState.asStateFlow() }

    protected val currentState: S
        get() = uiState.value

    private val _effectChannel: Channel<E> = Channel()
    val effectStream: Flow<E> = _effectChannel.receiveAsFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    /**
     * Updates the [state] atomically using the specified [mutation] of its value.
     * [mutation] may be evaluated multiple times, if value is being concurrently updated.
     *
     * @param mutation the mapping function to be applied to update the [state].
     */
    protected fun updateState(mutation: (S) -> S) = _uiState.update {
        mutation(it)
    }

    /**
     * Delivers a new value to the [state].
     *
     * Passing a new value that is [equal][Any.equals] to the [currentState] will not update the
     * [state] property.
     *
     * It is recommended to use [updateState] function to avoid race conditions.
     *
     * @param state a new instance of [S] to emit.
     */
    open fun emitState(state: S) {
        if (BuildConfig.DEBUG) Log.d(this::class.simpleName, "NEW STATE : $state")
        _uiState.value = state
    }

    fun emitEffect(effect: E) {
        viewModelScope.launch(Dispatchers.Main.immediate + exceptionHandler) {
            _effectChannel.send(effect)
        }
    }
}
