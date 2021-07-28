package ir.liyanamarket.predictlive.model.changeprofile

import ir.liyanamarket.predictlive.`interface`.ApiServicesChangeProfile
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnectToChangeProfile {
    fun change():ApiServicesChangeProfile{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://www.liyanamarket.ir/")
            .build().create(ApiServicesChangeProfile::class.java)
    }
}