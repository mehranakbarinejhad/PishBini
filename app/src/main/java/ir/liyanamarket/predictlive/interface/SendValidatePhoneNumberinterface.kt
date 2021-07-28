package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ValidatePhoneNumber

interface SendValidatePhoneNumberinterface {
    fun onsuccess(list: List<ValidatePhoneNumber>)
    fun onerror(t: Throwable)
}