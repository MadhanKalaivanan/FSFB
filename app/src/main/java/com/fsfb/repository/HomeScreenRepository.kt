package com.fsfb.repository

import com.fsfb.module.dto.DataClass
import com.fsfb.services.HomeScreenService
import com.fsfb.services.RemoteError
import com.fsfb.utils.ConnectivityObserver
import com.fsfb.utils.Result
import javax.inject.Inject

class HomeScreenRepository @Inject constructor(
    private val homeScreenService: HomeScreenService,
    private val connectivityObserver: ConnectivityObserver
) {

    suspend fun getHomeScreenData(): Result<ArrayList<DataClass>>{
        if (!connectivityObserver.isConnected) {
            return Result.Failure(RemoteError.networkUnAvailable)
        }
        try {
            val response = homeScreenService.getHomeList("2000")
            response.result?.let {
                return Result.Success(it)
            }
            return Result.Failure(RemoteError.unknownError)
        } catch (e: Exception) {
            return Result.Failure(RemoteError.unknownError)
        }
    }

}