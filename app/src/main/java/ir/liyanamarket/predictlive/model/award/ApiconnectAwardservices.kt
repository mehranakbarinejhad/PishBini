package ir.liyanamarket.predictlive.model.award

import ir.liyanamarket.predictlive.`interface`.ApiServicesAward
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiconnectAwardservices {
    fun showtext():ApiServicesAward{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesAward::class.java)
    }
}