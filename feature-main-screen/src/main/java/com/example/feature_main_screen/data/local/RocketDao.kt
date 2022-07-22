package com.example.feature_main_screen.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy
import com.example.feature_main_screen.data.local.entity.*
import kotlinx.coroutines.flow.Flow
import com.example.feature_main_screen.domain.models.RocketInfo

@Dao
interface RocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRocket(rockets: List<RocketDbEntity>)

    @Query("SELECT * FROM rockets")
    fun getAll(): Flow<List<RocketDbEntity>>

    @Query("DELETE FROM rockets")
    suspend fun deleteAll()

/*
    @Query
    ("SELECT * FROM rockets WHERE id = :id")
    suspend fun getRocketInfo(id: String): List<RocketInfo>

    @Transaction
    @Query("SELECT * FROM rockets WHERE id = :id")
    suspend fun getStageList(id: String): List<RocketWithStage>

    @Transaction
    @Query("SELECT * FROM rockets WHERE id = :id")
    suspend fun getFlickrImageList(id: String): List<RocketWithFlickrImage>

    @Transaction
    @Query("SELECT * FROM rockets WHERE id = :id")
    suspend fun getPayloadWeightList(id: String): List<RocketWithPayloadWeight>
*/

}