package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Item

interface SendSelectItem {

    fun onsuccessitem(list: List<Item>)
    fun onerroritem(t:Throwable)
}