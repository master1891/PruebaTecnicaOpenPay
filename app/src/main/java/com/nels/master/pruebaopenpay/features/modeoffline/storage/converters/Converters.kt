package com.nels.master.pruebaopenpay.features.modeoffline.storage.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {

    @TypeConverter
    fun fromBitMap(bitmap: Bitmap):ByteArray{
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream)
        return  outputStream.toByteArray()
    }

    @TypeConverter
    fun ToBitmap(byteArray: ByteArray):Bitmap{
       return BitmapFactory.decodeByteArray(byteArray,0, byteArray.size)
    }
}