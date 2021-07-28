package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Buy
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServicesSuccessBuy {
    @FormUrlEncoded
    @POST("Main.php")
    fun showSuccessbuy(
        @Field("action")action:String,
        @Field("username")username:String

    ):Call<List<Buy>>
}