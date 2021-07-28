package ir.liyanamarket.predictlive.model.user

import ir.liyanamarket.predictlive.`interface`.ApiServicesUsersByPhoneNumber
import ir.liyanamarket.predictlive.`interface`.ApiservicesUsers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiConnectUsers {
    fun getdata(): ApiservicesUsers {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiservicesUsers::class.java)
    }

    fun getdatabynumber():ApiServicesUsersByPhoneNumber{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesUsersByPhoneNumber::class.java)
    }

}