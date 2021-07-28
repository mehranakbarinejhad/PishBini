package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.InsertBasketResult

interface SendinsertBasket {

    fun onsuccessBasket(list: List<InsertBasketResult>)
    fun onerrorbasket(t:Throwable)
}