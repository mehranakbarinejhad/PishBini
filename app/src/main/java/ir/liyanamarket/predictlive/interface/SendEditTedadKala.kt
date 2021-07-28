package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ShowBasket

interface SendEditTedadKala {
    fun onsuccessEditTedadKala(list: List<ShowBasket>)
    fun onerrorEditTedadKala(t:Throwable)
}