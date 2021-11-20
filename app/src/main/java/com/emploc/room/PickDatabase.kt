package com.emploc.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.emploc.model.aniketMotorsModel.AniketMotorsListResponse

@Database(entities = [AniketMotorsListResponse.Data::class], version = 1)
//@TypeConverters(Converters::class)
abstract class PickDatabase : RoomDatabase() {
    abstract fun recentDao(): ResultDao
}
