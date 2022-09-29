package com.fsfb.services

import com.fsfb.common.Constants
import com.fsfb.module.dto.HomeScreenListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeScreenService {
    @GET(Constants.GET_HOME_LIST)
    suspend fun getHomeList(@Query("results") results: String): HomeScreenListResponse

}