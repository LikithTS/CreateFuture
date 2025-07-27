package com.createfuture.takehome.data.remote

import com.createfuture.takehome.data.models.characterDto.CharacterResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * I am adding default value to authorization. Just keeping it simple.
 * Instead we can save in storage and use it in all the api.
 * So this will be secured.
 */
interface CFApiInterface {

    @GET("/characters")
    suspend fun getCharacters(@Header("Authorization") token: String = "Bearer 754t!si@glcE2qmOFEcN"): Response<List<CharacterResponseItem>>

}