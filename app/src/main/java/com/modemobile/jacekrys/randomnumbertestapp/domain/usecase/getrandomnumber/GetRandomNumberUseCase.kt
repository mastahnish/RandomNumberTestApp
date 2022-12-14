package com.modemobile.jacekrys.randomnumbertestapp.domain.usecase.getrandomnumber

import com.modemobile.jacekrys.randomnumbertestapp.common.Resource
import com.modemobile.jacekrys.randomnumbertestapp.domain.repository.RandomNumberRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRandomNumberUseCase @Inject constructor(
    private val repository: RandomNumberRepository
) {
    operator fun invoke() = flow {
        try {
            emit(Resource.Loading())
            val number = repository.getRandomNumber()
            emit(Resource.Success(number))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Some unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}