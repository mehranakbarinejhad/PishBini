package ir.liyanamarket.predictlive.dataclass

data class Predict(
    var username:String,
    var matchid:Int,
    var hometeamgols:Int,
    var guestteamgols:Int,
    var scorematch:String
)