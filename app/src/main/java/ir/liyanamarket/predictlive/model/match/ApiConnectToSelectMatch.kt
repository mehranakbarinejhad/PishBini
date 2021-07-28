package ir.liyanamarket.predictlive.model.match

import ir.liyanamarket.predictlive.`interface`.ApiServicesSelectMatch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectToSelectMatch {
    fun selectmatch():ApiServicesSelectMatch{

        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/")
            .build().create(ApiServicesSelectMatch::class.java)

    }
}