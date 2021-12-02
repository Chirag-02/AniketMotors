package com.aniketassociates.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aniketassociates.model.aniketMotorsModel.AniketMotorsListResponse

@Database(entities = [AniketMotorsListResponse.Data::class], version = 1)
//@TypeConverters(Converters::class)
abstract class PickDatabase : RoomDatabase() {
    abstract fun recentDao(): ResultDao
}
