package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Award
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServicesAward {
    @FormUrlEncoded
    @POST("Main2.php")
    fun showtext(
        @Field("action")action:String
    ):Call<Award>
}