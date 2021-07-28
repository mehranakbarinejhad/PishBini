package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.SmsCode
import retrofit2.Call
import retrofit2.http.*

interface ApiServicesendCode {
    @FormUrlEncoded
    @POST("Main.php")
    fun sendcode(
        @Field("action") action: String,
        @Field("phonenumber") phonenumber: String
    ): Call<List<SmsCode>>
}