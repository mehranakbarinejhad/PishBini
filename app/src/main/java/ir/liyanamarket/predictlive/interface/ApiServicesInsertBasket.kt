package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.InsertBasketResult
import retrofit2.Call
import retrofit2.http.*

interface ApiServicesInsertBasket {
    @FormUrlEncoded
    @POST("Main2.php")
    fun insertbasket(
        @Field("action")action:String,
        @Field("username")username:String,
        @Field("codekala")codekala:String,
        @Field("tedadkala")tedadkala:String,
        @Field("sizekala")sizekala:String


    ):Call<List<InsertBasketResult>>
}