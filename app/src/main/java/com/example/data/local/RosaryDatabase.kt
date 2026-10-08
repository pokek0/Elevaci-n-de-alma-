package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.CustomPrayer
import com.example.data.model.PrayerRecord
import com.example.data.model.ScheduleCheck

@Database(entities = [PrayerRecord::class, CustomPrayer::class, ScheduleCheck::class], version = 3, exportSchema = false)
abstract class RosaryDatabase : RoomDatabase() {
    abstract fun rosaryDao(): RosaryDao

    companion object {
        @Volatile
        private var INSTANCE: RosaryDatabase? = null

        fun getDatabase(context: Context): RosaryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RosaryDatabase::class.java,
                    "rosary_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
