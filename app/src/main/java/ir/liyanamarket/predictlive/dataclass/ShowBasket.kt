package ir.liyanamarket.predictlive.dataclass

data class ShowBasket(
    val id: String,
    val username: String,
    val tedadkala: String,
    val codekala:String,
    val kala:List<Kala>,
    val sizekala: String

)

