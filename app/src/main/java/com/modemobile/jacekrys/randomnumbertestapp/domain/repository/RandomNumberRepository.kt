package com.modemobile.jacekrys.randomnumbertestapp.domain.repository

interface RandomNumberRepository {

    suspend fun getRandomNumber(): Int
}