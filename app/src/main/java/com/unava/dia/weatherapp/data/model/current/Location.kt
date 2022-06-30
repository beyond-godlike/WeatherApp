package com.unava.dia.weatherapp.data.model.current

data class Location(
    var name: String? = null,
    var region: String? = null,
    var country: String? = null,
    var lat: Double? = null,
    var lon: Double? = null,
    var tz_id: String? = null,
    var localtime_epoch: Double? = null,
    var localtime: String? = null,
)