package ir.liyanamarket.predictlive.`interface`.footballapi

import ir.liyanamarket.predictlive.dataclass.apifootball.league.League
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServicesSelectLeague {
    @FormUrlEncoded
    @POST("Main3.php")
    fun selectgroupleague(
        @Field("action")action:String
    ):Call<List<League>>
}