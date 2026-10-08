package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prayer_records")
data class PrayerRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val mysteryType: String,
    val dateMillis: Long = System.currentTimeMillis(),
    val dateFormatted: String,
    val intention: String = "",
    val completed: Boolean = true
)
