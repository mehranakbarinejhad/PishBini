package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ValidatePhoneNumber
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceValidatePhoneNumber
{
    @FormUrlEncoded
    @POST("Main.php")
    fun resultnumber(
        @Field("action") action: String,
        @Field("phonenumber") phonenumber: String
    ): Call<List<ValidatePhoneNumber>>
}