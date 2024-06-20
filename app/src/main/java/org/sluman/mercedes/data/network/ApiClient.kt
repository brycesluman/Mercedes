package org.sluman.mercedes.data.network

import android.content.Context
import com.itkacher.okprofiler.BuildConfig
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class RetrofitClient(applicationContext: Context) {
    val retrofit: Retrofit by lazy {
        val builder = OkHttpClient.Builder()
            .cache(
                Cache(
                    File(applicationContext.cacheDir, "http-cache"),
                    10L * 1024L * 1024L
                )
            ) // 10 MiB
            .addNetworkInterceptor(CacheInterceptor())
            .addInterceptor(ForceCacheInterceptor(applicationContext))
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(OkHttpProfilerInterceptor())
        }
        val client = builder.build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }
}

class ApiClient(applicationContext: Context) {
    val apiService: ApiService by lazy {
        RetrofitClient(applicationContext).retrofit.create(ApiService::class.java)
    }
}