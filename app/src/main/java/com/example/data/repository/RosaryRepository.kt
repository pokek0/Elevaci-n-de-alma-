package com.example.data.repository

import com.example.data.local.RosaryDao
import com.example.data.model.CustomPrayer
import com.example.data.model.PrayerRecord
import kotlinx.coroutines.flow.Flow

class RosaryRepository(private val rosaryDao: RosaryDao) {
    val allRecords: Flow<List<PrayerRecord>> = rosaryDao.getAllRecords()
    val completedCount: Flow<Int> = rosaryDao.getCompletedCount()
    val allCustomPrayers: Flow<List<CustomPrayer>> = rosaryDao.getAllCustomPrayers()
    val favoriteCustomPrayers: Flow<List<CustomPrayer>> = rosaryDao.getFavoriteCustomPrayers()

    suspend fun saveCompletedRosary(mysteryType: String, intention: String) {
        val dateFormat = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault())
        val record = PrayerRecord(
            mysteryType = mysteryType,
            dateMillis = System.currentTimeMillis(),
            dateFormatted = dateFormat.format(java.util.Date()),
            intention = intention,
            completed = true
        )
        rosaryDao.insertRecord(record)
    }

    suspend fun deleteRecord(id: Long) {
        rosaryDao.deleteRecord(id)
    }

    suspend fun addCustomPrayer(prayer: CustomPrayer): Long {
        return rosaryDao.insertCustomPrayer(prayer)
    }

    suspend fun updateCustomPrayer(prayer: CustomPrayer) {
        rosaryDao.updateCustomPrayer(prayer)
    }

    suspend fun deleteCustomPrayer(id: Long) {
        rosaryDao.deleteCustomPrayer(id)
    }

    // Schedule Checks
    fun getScheduleChecksForDate(dateString: String): Flow<List<com.example.data.model.ScheduleCheck>> {
        return rosaryDao.getScheduleChecksForDate(dateString)
    }

    suspend fun toggleScheduleCheck(dateString: String, slotId: String, currentChecked: Boolean) {
        if (!currentChecked) {
            val check = com.example.data.model.ScheduleCheck(
                id = "${dateString}_${slotId}",
                dateString = dateString,
                slotId = slotId,
                isCompleted = true
            )
            rosaryDao.insertScheduleCheck(check)
        } else {
            rosaryDao.deleteScheduleCheck(dateString, slotId)
        }
    }
}
