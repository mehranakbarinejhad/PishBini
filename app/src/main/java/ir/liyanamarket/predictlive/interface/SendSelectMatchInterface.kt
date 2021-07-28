package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Match

interface SendSelectMatchInterface {
    fun onsuccessMatch(list: List<Match>)
    fun onerrorMatch(t:Throwable)
}