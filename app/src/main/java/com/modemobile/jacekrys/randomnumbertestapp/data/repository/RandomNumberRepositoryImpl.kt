package com.modemobile.jacekrys.randomnumbertestapp.data.repository

import com.modemobile.jacekrys.randomnumbertestapp.common.Constants.MAX_RANDOM_NUMBER
import com.modemobile.jacekrys.randomnumbertestapp.common.Constants.MIN_RANDOM_NUMBER
import com.modemobile.jacekrys.randomnumbertestapp.data.remote.RandomNumberApi
import com.modemobile.jacekrys.randomnumbertestapp.domain.repository.RandomNumberRepository
import javax.inject.Inject

class RandomNumberRepositoryImpl @Inject constructor (
    private val api: RandomNumberApi
) : RandomNumberRepository {

    override suspend fun getRandomNumber(): Int {
        return api.getNumber(MIN_RANDOM_NUMBER, MAX_RANDOM_NUMBER).first()
    }

}
