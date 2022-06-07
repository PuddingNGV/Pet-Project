package com.example.feature_main_screen.data

import android.app.Application
import com.example.feature_main_screen.data.remote.responce.ApiRockets
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


class RocketApp:Application() {

    lateinit var rocketApi: ApiRockets

    override fun onCreate() {
        super.onCreate()

        configureRetrofit()
    }

    private fun configureRetrofit() {
        // okhttp <-interceptor <- retrofit

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/")
            .client(okHttpClient)
            .build()

        rocketApi = retrofit.create(ApiRockets::class.java)
    }
}