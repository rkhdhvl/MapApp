package com.practice.maps_recyclerview.model

data class MapData (
    var placeName : String,
    var latitude : Double,
    var longitude : Double,
    var uniqueId : Int
)
{
    override fun toString(): String {
        return "MapData(placeName=$placeName,latitude='$latitude', longitude='$longitude')"
    }
}