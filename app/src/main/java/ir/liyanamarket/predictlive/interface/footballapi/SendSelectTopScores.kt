package ir.liyanamarket.predictlive.`interface`.footballapi

import ir.liyanamarket.predictlive.dataclass.apifootball.topscores.TopScores

interface SendSelectTopScores {
    fun onsuccesstopscores(list: TopScores)
    fun onerror(t:Throwable)
}