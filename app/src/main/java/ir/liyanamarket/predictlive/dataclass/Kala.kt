package ir.liyanamarket.predictlive.dataclass

import java.io.Serializable

data class Kala(
    val id:String,
    val titr:String,
    val name:String,
    val date:String,
    val groupkala:String,
    val image:String,
    val Countryimage:String,
    val companname:String,
    val description:String,
    val details:String,
    val price:String,
    val off:String,
    val priceafteroff:String,
    val size:String,
    val color:String
):Serializable