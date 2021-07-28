package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Predict

interface SendSelectPredictInterface {
    fun onsuccessPredict(list: List<Predict>)
    fun onerrorPredict(t:Throwable)
}