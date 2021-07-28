package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Buy

interface SendSelectBuy {
    fun onsuccessbylist(list: List<Buy>)
    fun onerrorbuylist(t:Throwable)
}