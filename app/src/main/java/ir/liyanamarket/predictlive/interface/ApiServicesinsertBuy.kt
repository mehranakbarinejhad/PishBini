package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Buy
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServicesinsertBuy {

    @FormUrlEncoded
    @POST("Main2.php")
    fun inserttoby(
        @Field("action")action:String,
         @Field("username")username:String,
         @Field("codesefaresh")codesefaresh:String,

         @Field("codekala")codekala:String,
         @Field("namekala")namekala:String,
         @Field("pricekala")pricekala:String,
         @Field("tedadkala")tedadkala:String,
         @Field("sizekala")sizekala:String,
         @Field("namekharidar")name:String,
         @Field("phonenumber")phonenumber:String,
         @Field("address")address:String,


    ):Call<List<Buy>>
}