package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ChangeprofileDetails
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServicesChangeprofileDetails {
    @FormUrlEncoded
    @POST("Main.php")
    fun changedetails(
        @Field("action")action:String,
         @Field("username")username:String,
        @Field("newusername")newusername:String,
         @Field("name")name:String,
         @Field("password")password:String,
         @Field("phonenumber")phonenumber:String,
        @Field("typechange")typechange:String,


    ):Call<ChangeprofileDetails>
}