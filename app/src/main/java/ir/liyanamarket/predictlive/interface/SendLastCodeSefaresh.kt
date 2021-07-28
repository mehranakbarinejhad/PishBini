package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ResultCodeSefaresh

interface SendLastCodeSefaresh {
    fun onsuccesslastcode(list: List<ResultCodeSefaresh>)
    fun onerrorlastcode(t:Throwable)
}
