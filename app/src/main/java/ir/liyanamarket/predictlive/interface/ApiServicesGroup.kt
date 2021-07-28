package ir.liyanamarket.predictlive.`interface`


import ir.liyanamarket.predictlive.dataclass.GroupKala
import retrofit2.Call
import retrofit2.http.GET


interface ApiServicesGroup {
    @GET("Main.php?action=selectgroupkala")
    fun getgroup():Call<List<GroupKala>>
}