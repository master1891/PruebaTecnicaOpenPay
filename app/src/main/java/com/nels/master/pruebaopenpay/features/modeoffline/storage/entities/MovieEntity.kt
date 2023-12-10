package com.nels.master.pruebaopenpay.features.modeoffline.storage.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val title:String,
    val typeSource:Int,
    val logo:Bitmap
)