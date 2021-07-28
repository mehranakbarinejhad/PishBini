package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Users

import retrofit2.Call
import retrofit2.http.*

interface ApiservicesUsers {
    @FormUrlEncoded
    @POST("Main.php")
        fun getusers(
            @Field("action")action:String,
            @Field("username")username:String
        ):Call<List<Users>>


}