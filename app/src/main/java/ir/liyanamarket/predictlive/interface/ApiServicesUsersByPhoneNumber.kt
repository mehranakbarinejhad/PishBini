package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Users
import retrofit2.Call
import retrofit2.http.*

interface ApiServicesUsersByPhoneNumber {
    @FormUrlEncoded
    @POST("Main.php")
    fun getusersbynumber(
        @Field("action")action:String,
        @Field("phonenumber")username:String
    ): Call<List<Users>>
}