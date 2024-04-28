package com.temetnosce.sadhana.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.temetnosce.sadhana.domain.model.DailyModel
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
) {

    fun mapToDomain() =
        DailyModel(
            id = id,
            date = date,
            awake = awake,
            service = service,
            kirtan = kirtan,
            books = books,
            lectures = lectures,
            sleep = sleep,
            japa7 = japa73,
            japa10 = japa10,
            japa18 = japa18,
            japa24 = japa24,
        )

    companion object {
        fun mapFromDomain(domain: DailyModel) =
            SadhanaEntity(
                id = domain.id,
                date = domain.date,
                awake = domain.awake,
                service = domain.service,
                kirtan = domain.kirtan,
                books = domain.books,
                lectures = domain.lectures,
                sleep = domain.sleep,
                japa73 = domain.japa7,
                japa10 = domain.japa10,
                japa18 = domain.japa18,
                japa24 = domain.japa24,
            )
    }
}
