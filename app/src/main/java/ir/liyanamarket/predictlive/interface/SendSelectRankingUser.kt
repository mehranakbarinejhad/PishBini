package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.RankingUser

interface SendSelectRankingUser {
    fun onsuccessrankinguser(list: List<RankingUser>)
    fun onerrorrankinguser(t:Throwable)
}