package ir.liyanamarket.predictlive.model.shop.group

import ir.liyanamarket.predictlive.`interface`.ApiServicesGroup
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectGroup {

    fun getgroup():ApiServicesGroup{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesGroup::class.java)
    }
}