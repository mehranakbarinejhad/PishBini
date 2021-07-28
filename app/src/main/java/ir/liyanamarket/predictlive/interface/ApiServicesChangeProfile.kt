package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ChangeProfile
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServicesChangeProfile {
    @FormUrlEncoded
    @POST("Main2.php")
    fun change(
        @Field("action")action:String,
        @Field("strimage")strimage:String,
        @Field("username")username:String,
    ): Call<List<ChangeProfile>>
}