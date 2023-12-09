package com.nels.master.pruebaopenpay.features.locationfeature.network

import android.accessibilityservice.TouchInteractionController.Callback
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.nels.master.pruebaopenpay.features.locationfeature.domain.RegisterLocationRepository
import com.nels.master.pruebaopenpay.features.locationfeature.domain.models.Posicion
import javax.inject.Inject
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class GetRegisterLocationRepositoryImpl @Inject constructor(
    private val firestoreDb: FirebaseFirestore
) : RegisterLocationRepository {
    override suspend fun registerLocation(posicion: Posicion): RegisterLocationRepository.ResultRegisterLocation {

        return suspendCoroutine { continuation ->
            val doc = firestoreDb.collection("locations")
             doc.add(posicion.convertToMap())
                .addOnSuccessListener {
                    continuation.resumeWith(Result.success(RegisterLocationRepository.ResultRegisterLocation.Success))
                }
                .addOnFailureListener {
                    continuation.resumeWith(Result.success(RegisterLocationRepository.ResultRegisterLocation.Error("No se pudo registrar")))
                }
        }

    }


}