package ir.liyanamarket.predictlive.model.shop.basket

import ir.liyanamarket.predictlive.`interface`.ApiServicesDeleteBasket
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectDeleteBasket {

    fun deletebasket():ApiServicesDeleteBasket{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesDeleteBasket::class.java)
    }
}