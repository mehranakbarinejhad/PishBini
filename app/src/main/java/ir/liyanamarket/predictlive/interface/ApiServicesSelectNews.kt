package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.News
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServicesSelectNews {
    @FormUrlEncoded
    @POST("Main2.php")
    fun selectnews(
        @Field("action")action:String
    ):Call<List<News>>
}