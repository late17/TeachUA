package com.teachuacompose.data.model.dto.clubs.unUsedClubs

import com.teachuacompose.data.model.dto.cities.City

data class Location(
    val address: String,
    val city: City,
    val cityId: Int,
    val cityName: String,
    val clubId: Any,
    val coordinates: Any,
    val districtId: Int,
    val districtName: String,
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val phone: Any,
    val stationId: Int,
    val stationName: String
)