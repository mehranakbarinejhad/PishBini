package ir.liyanamarket.predictlive.model.footballapi.league

import ir.liyanamarket.predictlive.`interface`.footballapi.ApiServicesSelectLeague
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectSelectLeague {
    fun selectleague():ApiServicesSelectLeague
    {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesSelectLeague::class.java)
    }
}