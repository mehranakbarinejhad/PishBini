package ir.liyanamarket.predictlive.model.register

import ir.liyanamarket.predictlive.`interface`.ApiServiceRegister
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectRegister {
    fun register(): ApiServiceRegister {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServiceRegister::class.java)
    }

}