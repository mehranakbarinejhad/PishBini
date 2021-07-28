package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Register
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceRegister {
    /*@GET("Main.php")
    fun register(
        @Query("action") action: String,
        @Query("name") Name: String,
        @Query("username") Username: String,
        @Query("password") Password: String,
        @Query("phonenumber") Phonenumber: String

    ): Call<List<Register>>*/

    @FormUrlEncoded
    @POST("Main.php")
    fun register(
        @Field("action") action: String,
        @Field("name") Name: String,
        @Field("username") Username: String,
        @Field("password") Password: String,
        @Field("phonenumber") Phonenumber: String

    ): Call<List<Register>>
}