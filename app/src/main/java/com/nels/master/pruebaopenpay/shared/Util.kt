package com.nels.master.pruebaopenpay.shared

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ClipDescription
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings.Global.getString
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.gson.Gson
import com.nels.master.pruebaopenpay.R
import okhttp3.ResponseBody
import retrofit2.Response

fun <T : Any> Response<T>.toDataOrError(
    defaultErrorMsg: String = "Error en la respuesta del servidor",
    customBodyMapping: ((Response<T>, T) -> T) = { _, t -> t }
): DataOrError<T> {
    val body = this.body()
    val errorBody = this.errorBody()
    return when {
        body != null -> {
            if (this.isSuccessful) DataOrError.Data(
                customBodyMapping(
                    this,
                    body
                )
            ) else DataOrError.Error(this.message() ?: defaultErrorMsg)
        }

        errorBody != null -> {
            DataOrError.Error(getMessageFromErrorBody(errorBody, defaultErrorMsg, code()))
        }

        else -> {
            val message =
                this.message().takeIf { it.isNullOrBlank() } ?: "$defaultErrorMsg (${code()})"
            DataOrError.Error(message)
        }
    }
}

private fun getMessageFromErrorBody(
    errorBody: ResponseBody,
    defaultMessage: String,
    responseCode: Int
): String = try {
    Gson().fromJson(errorBody.string(), Map::class.java)?.let { map ->
        (map["message"] ?: map["msg"] ?: map["mensaje"])?.toString()
    } ?: "$defaultMessage ($responseCode)"
} catch (e: Exception) {
    e.toString()
}

fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}







