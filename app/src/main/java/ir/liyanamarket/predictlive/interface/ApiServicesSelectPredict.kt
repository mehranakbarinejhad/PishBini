package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Predict
import retrofit2.Call
import retrofit2.http.*

interface ApiServicesSelectPredict {
    @FormUrlEncoded
    @POST("Main.php")
    fun selectpredicuser(
        @Field("action")action:String,
        @Field("username")username:String,
        ): Call<List<Predict>>
}