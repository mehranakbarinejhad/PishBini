package ir.liyanamarket.predictlive.model.news

import ir.liyanamarket.predictlive.`interface`.ApiServicesSelectNews
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectSelectNews {

    fun selectNews():ApiServicesSelectNews
    {
        return  Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesSelectNews::class.java)
    }
}