package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ShowBasket
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServicesEditTedadKala {
    @FormUrlEncoded
    @POST("Main2.php")
    fun edittedadkala(
        @Field("action") action: String,
        @Field("username") username: String,
        @Field("codekala") codekala: String,
        @Field("sizekala") sizekala: String,
        @Field("tedadkala") tedadkala: String


    ): Call<List<ShowBasket>>

}