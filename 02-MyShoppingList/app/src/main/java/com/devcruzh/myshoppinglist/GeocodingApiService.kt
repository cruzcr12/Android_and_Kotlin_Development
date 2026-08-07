package com.devcruzh.myshoppinglist

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GeocodingApiService {

    @GET("maps/api/geocode/json?")
    suspend fun getAddressFromCoordinates(
        @Query("latlng") latlng: String,
        @Query("key") apiKey: String,
        @Header("X-Android-Package") packageName: String,
        @Header("X-Android-Cert") certFingerprint: String
    ): GeocodingResponse
}