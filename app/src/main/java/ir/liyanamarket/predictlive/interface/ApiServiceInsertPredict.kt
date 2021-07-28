package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ResultPredict
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceInsertPredict {
  /*  @GET("Main.php")
    fun insertpredict(
        @Query("action")action:String,
        @Query("username")username:String,
        @Query("matchid")matchid:Int,
        @Query("hometeamgols")hometeamgols:Int,
        @Query("guestteamgols")guestteamgols:Int

    ): Call<List<ResultPredict>>*/

    @FormUrlEncoded
    @POST("Main.php")
    fun insertpredict(
        @Field("action")action:String,
        @Field("username")username:String,
        @Field("matchid")matchid:Int,
        @Field("hometeamgols")hometeamgols:Int,
        @Field("guestteamgols")guestteamgols:Int

    ): Call<List<ResultPredict>>
}