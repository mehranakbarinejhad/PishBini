package ir.liyanamarket.predictlive.model.shop.basket

import ir.liyanamarket.predictlive.`interface`.ApiServicesInsertBasket
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectInsertBasket {

    fun insertbasket():ApiServicesInsertBasket{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesInsertBasket::class.java)
    }
}