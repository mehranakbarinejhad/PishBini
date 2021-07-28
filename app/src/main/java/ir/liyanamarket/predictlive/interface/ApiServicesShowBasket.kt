package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ShowBasket
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServicesShowBasket {
    @FormUrlEncoded
    @POST("Main2.php")
    fun showbasket(
        @Field("action")action:String,
        @Field("username")username:String,

        ):Call<List<ShowBasket>>
}