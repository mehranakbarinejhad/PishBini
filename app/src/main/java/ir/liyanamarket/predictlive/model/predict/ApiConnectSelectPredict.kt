package ir.liyanamarket.predictlive.model.predict

import ir.liyanamarket.predictlive.`interface`.ApiServicesSelectPredict
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectSelectPredict {
    fun selectpredict(): ApiServicesSelectPredict {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/")
            .build().create(ApiServicesSelectPredict::class.java)
    }
}