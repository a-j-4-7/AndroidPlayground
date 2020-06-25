package com.example.demoapplication.network

import com.example.demoapplication.data.models.DropDownResponse
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface RetrofitClient {

    @GET("61ecc9c9-7dea-4b66-9723-1c193d457c9e")
    suspend fun getDropdownValues():Response<DropDownResponse>

    @GET("733c828e-3dcb-45c1-a64b-cbd3ca3a1efb")
    fun getCountries():Call<JsonElement>

    @GET("746678b2-5520-4db9-9390-dcf9486a0cc7")
    fun getCities(): Call<JsonElement>

    companion object{
        operator fun invoke() : RetrofitClient{

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(this.getLoggingInterceptor())
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("https://run.mocky.io/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitClient::class.java)
        }

        private fun getLoggingInterceptor(): Interceptor {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return loggingInterceptor
        }
    }

}