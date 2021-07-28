package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Kala

interface SendSelectKalaInterface {
    fun onsuccess(list: MutableList<Kala>)
    fun onerror(t:Throwable)
}