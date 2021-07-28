package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ChangeProfile

interface SendChangeProfileInterface {
    fun onsuccess(list: List<ChangeProfile>)
    fun onerror(t:Throwable)
}