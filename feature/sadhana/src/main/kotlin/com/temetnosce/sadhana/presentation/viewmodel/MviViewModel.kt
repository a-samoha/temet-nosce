@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.temetnosce.sadhana.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.temetnosce.sadhana.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<S : MviState, I : MviIntent, E : MviEffect> : ViewModel() {

    abstract val initialState: S

    protected val currentState: S
        get() = uiState.value

    private val _uiState: MutableStateFlow<S> by lazy { MutableStateFlow(initialState) }
    val uiState: StateFlow<S> by lazy { _uiState.asStateFlow() }

    private val _eventsChannel: Channel<E> = Channel()
    val eventsStream: Flow<E> = _eventsChannel.receiveAsFlow()

    /**
     * Callback function that supposed to receive an [intent] from UI layer.
     *
     * Should be overridden and implemented in
     */
    open fun processIntent(intent: I) = Unit

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
    protected fun emitState(state: S) {
        if (BuildConfig.DEBUG) Log.d(this::class.simpleName, "NEW STATE : $state")
        _uiState.value = state
    }

    /**
     * Delivers a new value to the [effect].
     *
     * @param effect a new instance of [E] to emit.
     */
    protected fun emitEffect(effect: E) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _eventsChannel.send(effect)
        }
    }
}
