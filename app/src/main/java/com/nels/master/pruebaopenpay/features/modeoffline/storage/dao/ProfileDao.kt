package com.nels.master.pruebaopenpay.features.modeoffline.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.ProfileEntity

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createProfile(profileEntity: ProfileEntity)

    @Query("SELECT * from ProfileEntity")
     fun getProfile(): List<ProfileEntity>

}

