package com.unava.dia.weatherapp.data.model.future

data class Hour(
    var time_epoch: Double? = null,
    var time: String? = null,
    var temp_c: Double? = null,
    var temp_f: Double? = null,
    var is_day: Double? = null,
    var condition: Condition? = null,
    var wind_mph: Double? = null,
    var wind_kph: Double? = null,
    var wind_degree: Double? = null,
    var wind_dir: String? = null,
    var pressure_mb: Double? = null,
    var pressure_in: Double? = null,
    var precip_mm: Double? = null,
    var precip_in: Double? = null,
    var humidity: Double? = null,
    var cloud: Double? = null,
    var feelslike_c: Double? = null,
    var feelslike_f: Double? = null,
    var windchill_c: Double? = null,
    var windchill_f: Double? = null,
    var heatindex_c: Double? = null,
    var heatindex_f: Double? = null,
    var dewpoint_c: Double? = null,
    var dewpoint_f: Double? = null,
    var will_it_rain: Double? = null,
    var chance_of_rain: Double? = null,
    var will_it_snow: Double? = null,
    var chance_of_snow: Double? = null,
    var vis_km: Double? = null,
    var vis_miles: Double? = null,
    var gust_mph: Double? = null,
    var gust_kph: Double? = null,
    var uv: Double? = null,
)