package ir.liyanamarket.predictlive.model.predict

import ir.liyanamarket.predictlive.`interface`.ApiServiceInsertPredict
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectinsertPredict {
    fun insertpredict():ApiServiceInsertPredict{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://www.liyanamarket.ir/")
            .build().create(ApiServiceInsertPredict::class.java)
    }
}