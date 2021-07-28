package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ShowBasket

interface SendDeleteBasket {
    fun onsuccessDeleteBasket(list: List<ShowBasket>)
    fun onerrorDeleteBasket(t:Throwable)
}