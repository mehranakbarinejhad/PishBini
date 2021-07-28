package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Match
import retrofit2.Call
import retrofit2.http.*

interface ApiServicesSelectMatch {
    @FormUrlEncoded
    @POST("Main.php")
    fun selectmatch(
        @Field("action")action:String,
        @Field("matchdate")matchdate:String

    ): Call<List<Match>>
}