package ir.liyanamarket.predictlive.model.shop.kala

import ir.liyanamarket.predictlive.`interface`.ApiServiceSelectKala
import ir.liyanamarket.predictlive.`interface`.ApiservicesUsers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectToSelectKala {

    fun select(): ApiServiceSelectKala {
        return  Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/")
            .build()
            .create(ApiServiceSelectKala::class.java)
    }
}