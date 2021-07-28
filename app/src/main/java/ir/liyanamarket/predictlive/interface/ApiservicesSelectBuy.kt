package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Buy
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiservicesSelectBuy {
    @FormUrlEncoded
    @POST("Main2.php")
    fun selectbycode(
        @Field("action")action:String,
         @Field("codesefaresh")code:String,

    ):Call<List<Buy>>
    fun selectbyusername(
        @Field("action")action:String,
        @Field("username")username:String,
    ):Call<List<Buy>>
}