package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ResultPredict

interface SendinsertpredictInterface {
    fun onsuccess(list: List<ResultPredict>)
    fun onerror(t:Throwable)
}