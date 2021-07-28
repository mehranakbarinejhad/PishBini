package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Buy

interface SendSelectSuccessBuy {
    fun onsuccess(list: List<Buy>)
    fun onerror(t:Throwable)
}