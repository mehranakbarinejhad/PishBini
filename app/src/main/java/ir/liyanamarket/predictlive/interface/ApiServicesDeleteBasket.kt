package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ShowBasket
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServicesDeleteBasket {
    @FormUrlEncoded
    @POST("Main2.php")
    fun deletebasket(
        @Field("action")action:String,
         @Field("username")username:String,
         @Field("codekala")codekala:String,
         @Field("sizekala")sizekala:String

    ):Call<List<ShowBasket>>
}