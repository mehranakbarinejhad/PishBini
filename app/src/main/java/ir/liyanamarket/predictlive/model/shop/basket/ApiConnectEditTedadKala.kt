package ir.liyanamarket.predictlive.model.shop.basket

import ir.liyanamarket.predictlive.`interface`.ApiServicesEditTedadKala
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectEditTedadKala {
    fun  edittedadakala():ApiServicesEditTedadKala{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesEditTedadKala::class.java)
    }
}