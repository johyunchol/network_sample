package kr.co.kkensu.network_sample

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("/test/search")
    fun search(
        @Query("query") keyword: String
    ): Call<GetSearchResponse>
}