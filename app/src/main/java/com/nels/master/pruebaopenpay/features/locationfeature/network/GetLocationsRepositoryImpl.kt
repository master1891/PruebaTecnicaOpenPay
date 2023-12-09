package com.nels.master.pruebaopenpay.features.locationfeature.network

import android.accessibilityservice.TouchInteractionController.Callback
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.nels.master.pruebaopenpay.features.locationfeature.domain.LocationsRepository
import com.nels.master.pruebaopenpay.features.locationfeature.domain.RegisterLocationRepository
import com.nels.master.pruebaopenpay.features.locationfeature.domain.models.Posicion
import javax.inject.Inject
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class GetLocationsRepositoryImpl @Inject constructor(
    private val firestoreDb: FirebaseFirestore
) : LocationsRepository {

    override suspend fun getLocationsBd(): LocationsRepository.ResultLocationsBd {
        return suspendCoroutine { continuation ->
            var positions = mutableListOf<Posicion>()
            val doc = firestoreDb.collection("locations")
            doc.get().addOnSuccessListener {

                for (document in it.documents) {
                    val position = Posicion(
                        latitud = document.get("latitud") as Double,
                        longitud = document.get("longitud") as Double,
                        fecha = document.get("fecha") as String
                    )
                    positions.add(position)
                }

                continuation.resumeWith(
                    Result.success(
                        LocationsRepository.ResultLocationsBd.Success(
                            positions
                        )
                    )
                )
            }.addOnFailureListener {
                continuation.resumeWith(
                    Result.success(
                        LocationsRepository.ResultLocationsBd.Error(
                            it.message?: "No se pudo consultar"
                        )
                    )
                )
            }
        }
    }


}