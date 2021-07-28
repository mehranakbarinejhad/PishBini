package ir.liyanamarket.predictlive.model.user

import ir.liyanamarket.predictlive.`interface`.ApiServicesChangeprofileDetails
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectChangeprofileDetails {

    fun changedetails():ApiServicesChangeprofileDetails{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://www.liyanamarket.ir/").build().create(ApiServicesChangeprofileDetails::class.java)
    }
}