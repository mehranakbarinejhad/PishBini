package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.GroupKala

interface SendGroupInterface {
    fun onsuccessgroup(list: List<GroupKala>)
    fun onerrorgroup(t:Throwable)
}