package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ResultCodeSefaresh
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServicesLastCodeSefaresh {
    @FormUrlEncoded
    @POST("Main2.php")
    fun selectlast(
        @Field( "action")action:String
    ):Call<List<ResultCodeSefaresh>>

}