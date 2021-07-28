package ir.liyanamarket.predictlive.model.user

import ir.liyanamarket.predictlive.`interface`.ApiServicesSelectRankingUser
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectRankingUser {
    fun getranking():ApiServicesSelectRankingUser{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/")
            .build()
            .create(ApiServicesSelectRankingUser::class.java)
    }
}