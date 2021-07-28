package ir.liyanamarket.predictlive.dataclass

data class Match (
    var matchid:Int,
    var matchdate:String,
    var matchhour:String,
    var hometeam:List<Teaminfo>,
    var homegols:Int,
    var guestteam:List<Teaminfo>,
    var teamguestgols:Int,
    var status:String
)
