package com.nels.master.pruebaopenpay.features.modeoffline.storage.impl

import com.nels.master.pruebaopenpay.features.modeoffline.domian.ProfileRegisterDbRepository
import com.nels.master.pruebaopenpay.features.modeoffline.storage.dao.ProfileDao
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.ProfileEntity
import javax.inject.Inject

class GetProfileRegisterDbImpl @Inject constructor(
    private val profileDao: ProfileDao
):ProfileRegisterDbRepository {


    override suspend fun registerProfileDb(profileEntity: ProfileEntity): ProfileRegisterDbRepository.ResultRegisterProfileDb {
        return try {
            profileDao.createProfile(profileEntity)
            ProfileRegisterDbRepository.ResultRegisterProfileDb.Success
        }catch (ex: Exception){
            ProfileRegisterDbRepository.ResultRegisterProfileDb.Error (ex.message?:"")
        }
    }

}