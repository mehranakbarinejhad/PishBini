package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Item
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServicesSelectItem {
    @FormUrlEncoded
    @POST("Main2.php")
    fun selectitem(
        @Field("action")action:String
    ):Call<List<Item>>
}