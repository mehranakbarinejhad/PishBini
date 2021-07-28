package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Register

interface SendRegisterinterface {
    fun onsuccess(list: List<Register>)
    fun onerror(t:Throwable)
}