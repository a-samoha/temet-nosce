@file:Suppress("unused")

package com.temetnosce.sadhana.presentation.viewmodel

interface MviState
interface MviIntent
interface MviEffect

object EmptyState : MviState
object EmptyIntent : MviIntent
object EmptyEffect : MviEffect
