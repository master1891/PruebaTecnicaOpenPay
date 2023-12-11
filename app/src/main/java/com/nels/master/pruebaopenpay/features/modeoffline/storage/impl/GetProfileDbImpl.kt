package com.nels.master.pruebaopenpay.features.modeoffline.storage.impl

import com.nels.master.pruebaopenpay.features.modeoffline.domian.ProfileDbRepository
import com.nels.master.pruebaopenpay.features.modeoffline.storage.dao.ProfileDao
import javax.inject.Inject

class GetProfileDbImpl  constructor(
    private val profileDao: ProfileDao
):ProfileDbRepository {

    override suspend fun getProfile(): ProfileDbRepository.ResultProfileDb {
        return try {
            val profile = profileDao.getProfile()
            ProfileDbRepository.ResultProfileDb.Success(profile)
        }catch (ex: Exception){
            ProfileDbRepository.ResultProfileDb.Error (ex.message?:"")
        }
    }

}