package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Kala
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceSelectKala {
    @FormUrlEncoded
    @POST("Main.php")
   fun selectKala(
       @Field("action")action:String,
        @Field("groupkala")group:String,
       @Field("sort")sort:String
   ):Call<MutableList<Kala>>

}