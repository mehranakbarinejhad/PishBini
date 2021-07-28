package ir.liyanamarket.predictlive.model.validatephonenumber

import ir.liyanamarket.predictlive.`interface`.ApiServiceValidatePhoneNumber
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectValidatePhoneNumber {
    fun result(): ApiServiceValidatePhoneNumber {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServiceValidatePhoneNumber::class.java)
    }
}