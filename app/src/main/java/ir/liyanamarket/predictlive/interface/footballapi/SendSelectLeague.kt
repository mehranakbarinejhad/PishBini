package ir.liyanamarket.predictlive.`interface`.footballapi

import ir.liyanamarket.predictlive.dataclass.apifootball.league.League

interface SendSelectLeague {
    fun onsuccessleague(list: List<League>)
    fun onerrorleague(t:Throwable)
}