package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Users

interface SendUsersInterface {
    fun onsuccess(list: List<Users>)
    fun onerror(t:Throwable)
}