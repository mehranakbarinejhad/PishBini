package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Award

interface SendAwardText {
    fun onsuccess(award:Award)
    fun onerror(t:Throwable)
}