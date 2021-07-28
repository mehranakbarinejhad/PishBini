package ir.liyanamarket.predictlive.model.shop.buy

import ir.liyanamarket.predictlive.`interface`.ApiServicesSuccessBuy
import ir.liyanamarket.predictlive.`interface`.ApiservicesSelectBuy
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectSelectsuccessBuy {

    fun selectsuccessbuy():ApiServicesSuccessBuy{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesSuccessBuy::class.java)
    }
}