package com.sunafil.mitienda.data.remote

import retrofit2.Response
import retrofit2.http.GET


/**
 * ImageAPI
 *
 * @author Bryam Soto
 * @since 19/12/22
 */
interface ApiService {

    @GET("hound/images")
    suspend fun getDogsByBread(): Response<DogResponse>

}