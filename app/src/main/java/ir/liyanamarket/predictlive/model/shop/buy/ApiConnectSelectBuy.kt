package ir.liyanamarket.predictlive.model.shop.buy

import ir.liyanamarket.predictlive.`interface`.ApiservicesSelectBuy
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectSelectBuy {

    fun selectbucode():ApiservicesSelectBuy{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiservicesSelectBuy::class.java)
    }

    fun selectbyusername():ApiservicesSelectBuy{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiservicesSelectBuy::class.java)
    }



}