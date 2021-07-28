package ir.liyanamarket.predictlive.model.sendcode

import ir.liyanamarket.predictlive.`interface`.ApiServicesendCode
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectSendCode {
    fun sendcode(): ApiServicesendCode {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesendCode::class.java)

    }
}