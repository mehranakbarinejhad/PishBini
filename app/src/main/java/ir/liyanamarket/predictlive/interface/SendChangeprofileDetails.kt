package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ChangeprofileDetails

interface SendChangeprofileDetails {
    fun onsuccesschangeprofiledetails(result:ChangeprofileDetails)
    fun onerrorchangeprofiledetails(t:Throwable)
}