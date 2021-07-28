package ir.liyanamarket.predictlive.model.shop.basket

import ir.liyanamarket.predictlive.`interface`.ApiServicesShowBasket
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectShowBasket {
    fun showbasket():ApiServicesShowBasket{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build()
            .create(ApiServicesShowBasket::class.java)
    }
}