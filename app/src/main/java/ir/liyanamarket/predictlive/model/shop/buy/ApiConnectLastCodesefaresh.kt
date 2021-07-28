package ir.liyanamarket.predictlive.model.shop.buy

import ir.liyanamarket.predictlive.`interface`.ApiServicesLastCodeSefaresh
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectLastCodesefaresh {

    fun selectlast():ApiServicesLastCodeSefaresh{
        return  Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesLastCodeSefaresh::class.java)
    }
}