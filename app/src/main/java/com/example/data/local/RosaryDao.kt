package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.CustomPrayer
import com.example.data.model.PrayerRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface RosaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: PrayerRecord): Long

    @Query("SELECT * FROM prayer_records ORDER BY dateMillis DESC")
    fun getAllRecords(): Flow<List<PrayerRecord>>

    @Query("SELECT COUNT(*) FROM prayer_records WHERE completed = 1")
    fun getCompletedCount(): Flow<Int>

    @Query("DELETE FROM prayer_records WHERE id = :recordId")
    suspend fun deleteRecord(recordId: Long)

    // Custom Prayers
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomPrayer(prayer: CustomPrayer): Long

    @Update
    suspend fun updateCustomPrayer(prayer: CustomPrayer)

    @Query("DELETE FROM custom_prayers WHERE id = :prayerId")
    suspend fun deleteCustomPrayer(prayerId: Long)

    @Query("SELECT * FROM custom_prayers ORDER BY dateCreatedMillis DESC")
    fun getAllCustomPrayers(): Flow<List<CustomPrayer>>

    @Query("SELECT * FROM custom_prayers WHERE isFavorite = 1 ORDER BY dateCreatedMillis DESC")
    fun getFavoriteCustomPrayers(): Flow<List<CustomPrayer>>

    // Daily Prayer Schedule Checks
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScheduleCheck(check: com.example.data.model.ScheduleCheck)

    @Query("DELETE FROM schedule_checks WHERE dateString = :dateString AND slotId = :slotId")
    suspend fun deleteScheduleCheck(dateString: String, slotId: String)

    @Query("SELECT * FROM schedule_checks WHERE dateString = :dateString")
    fun getScheduleChecksForDate(dateString: String): Flow<List<com.example.data.model.ScheduleCheck>>
}
