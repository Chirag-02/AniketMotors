package com.emploc.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emploc.model.aniketMotorsModel.AniketMotorsListResponse

@Dao
interface ResultDao {

    @Query("SELECT * FROM result where regNo LIKE :regNo || '%' and regCity LIKE :city || '%'" )
    suspend fun getAllRecords(city: String, regNo: String,): List<AniketMotorsListResponse.Data>?

    @Query("SELECT * FROM result limit 500")
    suspend fun getAllHundredRecords(): List<AniketMotorsListResponse.Data>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(provider: List<AniketMotorsListResponse.Data>)

}
