package ir.liyanamarket.predictlive.model.shop.buy

import ir.liyanamarket.predictlive.`interface`.ApiServicesinsertBuy
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectInsertBuy {
    fun inserttobuy():ApiServicesinsertBuy{
     return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
         .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesinsertBuy::class.java)
    }
}