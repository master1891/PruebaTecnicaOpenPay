package com.nels.master.pruebaopenpay.features.modeoffline.storage.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProfileEntity(
    @PrimaryKey
    val id:Int? = null,
    val userName:String,
)