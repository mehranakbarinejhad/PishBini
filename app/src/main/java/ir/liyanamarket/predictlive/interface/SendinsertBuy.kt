package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Buy

interface SendinsertBuy {
fun onsuucessinsertbuy(list: List<Buy>)
fun onerrorinsertbuy(t:Throwable)

}