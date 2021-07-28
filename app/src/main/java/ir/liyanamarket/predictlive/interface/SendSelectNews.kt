package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.News

interface SendSelectNews {

    fun onsuccessSelectNews(list: List<News>)
    fun onerrorSelectNews(t:Throwable)
}