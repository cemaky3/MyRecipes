package com.example.myapplication11.Retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Builder {
    object RecipeApiClient {

        private const val BASE_URL = "https://61e46d241a976f00176ee4a1.mockapi.io/api/v1/"

        var inteceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private var client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(inteceptor)
            .build()

        val apiClient: RecipeApiInterface by lazy {
         val retrofit = Retrofit.Builder()
             .baseUrl(BASE_URL)
             .client(client)
             .addConverterFactory(GsonConverterFactory.create())
             .build()

            return@lazy retrofit.create(RecipeApiInterface::class.java)
        }
    }
}