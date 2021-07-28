package ir.liyanamarket.predictlive.`interface`.footballapi

import ir.liyanamarket.predictlive.dataclass.apifootball.topscores.TopScores
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServicesSelectTopScores {
    @FormUrlEncoded
    @POST("Main3.php")
    fun selecttopscore(
        @Field("action")action:String,
        @Field("codeleague")codeleague:String
    ):Call<TopScores>
}