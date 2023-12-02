package com.artsam.sadhana.domain.model

import java.util.Date

data class SadhanaDayModel(
    val date: Date,
    val awake: String,
    val service: Boolean,
    val kirtan: Boolean,
    val books: Short,
    val lectures: Boolean,
    val sleep: String,
    val japa73: Short,
    val japa10: Short,
    val japa18: Short,
    val japa24: Short,
)
