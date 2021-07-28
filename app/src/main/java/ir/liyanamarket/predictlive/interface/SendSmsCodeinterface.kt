package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.SmsCode

interface SendSmsCodeinterface {
    fun onsuccess(list: List<SmsCode>)
    fun onerror(t:Throwable)
}