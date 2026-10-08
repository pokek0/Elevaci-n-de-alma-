package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule_checks")
data class ScheduleCheck(
    @PrimaryKey
    val id: String, // format: "yyyy-MM-dd_slotId"
    val dateString: String, // "yyyy-MM-dd"
    val slotId: String, // "DESPERTAR", "LAUDES", "MEDIODIA", "MISERICORDIA", "VISPERAS", "COMPLETAS"
    val isCompleted: Boolean,
    val completedAtMillis: Long = System.currentTimeMillis()
)
