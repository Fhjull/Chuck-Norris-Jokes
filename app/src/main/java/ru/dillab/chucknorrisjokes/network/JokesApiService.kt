package ru.dillab.chucknorrisjokes.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.icndb.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * We get [numberOfJokes] as String with number from editText view in [JokesFragment]
 */
interface JokesApiService {
    @GET("jokes/random/{numberOfJokes}")
    suspend fun getProperties(@Path("numberOfJokes") numberOfJokes: String?): ResponseProperty
}

object JokesApi {
    val retrofitService: JokesApiService by lazy {
        retrofit.create(JokesApiService::class.java)
    }
}
