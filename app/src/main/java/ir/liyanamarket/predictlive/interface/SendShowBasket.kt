package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ShowBasket

interface SendShowBasket {
    fun onsuccessShowBasket(list: List<ShowBasket>)
    fun onerrorShowBasket(t:Throwable)
}