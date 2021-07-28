package ir.liyanamarket.predictlive.model.item

import ir.liyanamarket.predictlive.`interface`.ApiServicesSelectItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectItem {
    fun selectitem():ApiServicesSelectItem{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesSelectItem::class.java)
    }
}