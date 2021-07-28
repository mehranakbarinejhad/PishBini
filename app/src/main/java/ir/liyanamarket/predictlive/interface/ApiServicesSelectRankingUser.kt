package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.RankingUser
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServicesSelectRankingUser {
    @FormUrlEncoded
    @POST("Main2.php")
    fun selectranking(
        @Field("action")action:String,
        @Field("username")username:String
    ):Call<List<RankingUser>>
}