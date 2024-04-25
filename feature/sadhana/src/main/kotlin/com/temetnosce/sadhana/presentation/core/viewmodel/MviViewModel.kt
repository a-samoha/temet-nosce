@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.temetnosce.sadhana.presentation.core.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.temetnosce.sadhana.BuildConfig
import com.temetnosce.sadhana.presentation.core.ui.MviEffect
import com.temetnosce.sadhana.presentation.core.ui.MviState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<S : MviState, E : MviEffect> : ViewModel() {

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

    open fun updateState(state: S) {
        if (BuildConfig.DEBUG) {
            Log.d(this::class.simpleName, "NEW STATE : $state")
        }
        _uiState.tryEmit(state)
    }

    fun emitEffect(effect: E) {
        viewModelScope.launch(Dispatchers.Main.immediate + exceptionHandler) {
            _effectChannel.send(effect)
        }
    }
}
