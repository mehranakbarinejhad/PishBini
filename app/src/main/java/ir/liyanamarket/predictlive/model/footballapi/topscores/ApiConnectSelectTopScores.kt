package ir.liyanamarket.predictlive.model.footballapi.topscores

import ir.liyanamarket.predictlive.`interface`.footballapi.ApiServicesSelectTopScores
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectSelectTopScores {

    fun select():ApiServicesSelectTopScores{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesSelectTopScores::class.java)
    }
}