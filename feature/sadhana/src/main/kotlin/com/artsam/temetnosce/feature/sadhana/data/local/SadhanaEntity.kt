package com.artsam.temetnosce.feature.sadhana.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = SadhanaContract.TABLE)
class SadhanaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SadhanaContract.ID)
    val id: Long,
    @ColumnInfo(name = SadhanaContract.DATE)
    val date: Date,
    @ColumnInfo(name = SadhanaContract.AWAKE)
    val awake: String,
    @ColumnInfo(name = SadhanaContract.SERVICE)
    val service: Boolean,
    @ColumnInfo(name = SadhanaContract.KIRTAN)
    val kirtan: Boolean,
    @ColumnInfo(name = SadhanaContract.BOOKS)
    val books: Short,
    @ColumnInfo(name = SadhanaContract.LECTURES)
    val lectures: Boolean,
    @ColumnInfo(name = SadhanaContract.SLEEP)
    val sleep: String,
    @ColumnInfo(name = SadhanaContract.JAPA73)
    val japa73: Short,
    @ColumnInfo(name = SadhanaContract.JAPA10)
    val japa10: Short,
    @ColumnInfo(name = SadhanaContract.JAPA18)
    val japa18: Short,
    @ColumnInfo(name = SadhanaContract.JAPA24)
    val japa24: Short,
)
