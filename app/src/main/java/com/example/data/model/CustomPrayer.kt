package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "custom_prayers")
data class CustomPrayer(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val subtitle: String = "",
    val category: String = "Petición",
    val content: String,
    val dateCreatedMillis: Long = System.currentTimeMillis(),
    val isFavorite: Boolean = false
)
