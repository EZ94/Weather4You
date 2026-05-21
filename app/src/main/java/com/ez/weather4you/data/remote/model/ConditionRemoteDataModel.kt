package com.ez.weather4you.data.remote.model

import com.ez.weather4you.domain.entity.forecast.Condition
import kotlinx.serialization.Serializable

@Serializable
data class ConditionRemoteDataModel(
    val text: String,
    val icon: String,
    val code: Int
)

fun ConditionRemoteDataModel.toDomain(isDay: Boolean): Condition = when (code) {
    1000 -> if (isDay) Condition.DAY_SUNNY else Condition.NIGHT_CLEAR
    1003 -> if (isDay) Condition.DAY_PARTLY_CLOUDY else Condition.NIGHT_PARTLY_CLOUDY
    1006 -> if (isDay) Condition.DAY_CLOUDY else Condition.NIGHT_CLOUDY
    1009 -> if (isDay) Condition.DAY_OVERCAST else Condition.NIGHT_OVERCAST
    1030 -> if (isDay) Condition.DAY_MIST else Condition.NIGHT_MIST
    1063 -> if (isDay) Condition.DAY_PATCHY_RAIN_POSSIBLE else Condition.NIGHT_PATCHY_RAIN_POSSIBLE
    1066 -> if (isDay) Condition.DAY_PATCHY_SNOW_POSSIBLE else Condition.NIGHT_PATCHY_SNOW_POSSIBLE
    1069 -> if (isDay) Condition.DAY_PATCHY_SLEET_POSSIBLE else Condition.NIGHT_PATCHY_SLEET_POSSIBLE
    1072 -> if (isDay) Condition.DAY_PATCHY_FREEZING_DRIZZLE_POSSIBLE else Condition.NIGHT_PATCHY_FREEZING_DRIZZLE_POSSIBLE
    1087 -> if (isDay) Condition.DAY_THUNDERY_OUTBREAKS_POSSIBLE else Condition.NIGHT_THUNDERY_OUTBREAKS_POSSIBLE
    1114 -> if (isDay) Condition.DAY_BLOWING_SNOW else Condition.NIGHT_BLOWING_SNOW
    1117 -> if (isDay) Condition.DAY_BLIZZARD else Condition.NIGHT_BLIZZARD
    1135 -> if (isDay) Condition.DAY_FOG else Condition.NIGHT_FOG
    1147 -> if (isDay) Condition.DAY_FREEZING_FOG else Condition.NIGHT_FREEZING_FOG
    1150 -> if (isDay) Condition.DAY_PATCHY_LIGHT_DRIZZLE else Condition.NIGHT_PATCHY_LIGHT_DRIZZLE
    1153 -> if (isDay) Condition.DAY_LIGHT_DRIZZLE else Condition.NIGHT_LIGHT_DRIZZLE
    1168 -> if (isDay) Condition.DAY_FREEZING_DRIZZLE else Condition.NIGHT_FREEZING_DRIZZLE
    1171 -> if (isDay) Condition.DAY_HEAVY_FREEZING_DRIZZLE else Condition.NIGHT_HEAVY_FREEZING_DRIZZLE
    1180 -> if (isDay) Condition.DAY_PATCHY_LIGHT_RAIN else Condition.NIGHT_PATCHY_LIGHT_RAIN
    1183 -> if (isDay) Condition.DAY_LIGHT_RAIN else Condition.NIGHT_LIGHT_RAIN
    1186 -> if (isDay) Condition.DAY_MODERATE_RAIN_AT_TIMES else Condition.NIGHT_MODERATE_RAIN_AT_TIMES
    1189 -> if (isDay) Condition.DAY_MODERATE_RAIN else Condition.NIGHT_MODERATE_RAIN
    1192 -> if (isDay) Condition.DAY_HEAVY_RAIN_AT_TIMES else Condition.NIGHT_HEAVY_RAIN_AT_TIMES
    1195 -> if (isDay) Condition.DAY_HEAVY_RAIN else Condition.NIGHT_HEAVY_RAIN
    1198 -> if (isDay) Condition.DAY_LIGHT_FREEZING_RAIN else Condition.NIGHT_LIGHT_FREEZING_RAIN
    1201 -> if (isDay) Condition.DAY_MODERATE_OR_HEAVY_FREEZING_RAIN else Condition.NIGHT_MODERATE_OR_HEAVY_FREEZING_RAIN
    1204 -> if (isDay) Condition.DAY_LIGHT_SLEET else Condition.NIGHT_LIGHT_SLEET
    1207 -> if (isDay) Condition.DAY_MODERATE_OR_HEAVY_SLEET else Condition.NIGHT_MODERATE_OR_HEAVY_SLEET
    1210 -> if (isDay) Condition.DAY_PATCHY_LIGHT_SNOW else Condition.NIGHT_PATCHY_LIGHT_SNOW
    1213 -> if (isDay) Condition.DAY_LIGHT_SNOW else Condition.NIGHT_LIGHT_SNOW
    1216 -> if (isDay) Condition.DAY_PATCHY_MODERATE_SNOW else Condition.NIGHT_PATCHY_MODERATE_SNOW
    1219 -> if (isDay) Condition.DAY_MODERATE_SNOW else Condition.NIGHT_MODERATE_SNOW
    1222 -> if (isDay) Condition.DAY_PATCHY_HEAVY_SNOW else Condition.NIGHT_PATCHY_HEAVY_SNOW
    1225 -> if (isDay) Condition.DAY_HEAVY_SNOW else Condition.NIGHT_HEAVY_SNOW
    1237 -> if (isDay) Condition.DAY_ICE_PELLETS else Condition.NIGHT_ICE_PELLETS
    1240 -> if (isDay) Condition.DAY_LIGHT_RAIN_SHOWER else Condition.NIGHT_LIGHT_RAIN_SHOWER
    1243 -> if (isDay) Condition.DAY_MODERATE_OR_HEAVY_RAIN_SHOWER else Condition.NIGHT_MODERATE_OR_HEAVY_RAIN_SHOWER
    1246 -> if (isDay) Condition.DAY_TORRENTIAL_RAIN_SHOWER else Condition.NIGHT_TORRENTIAL_RAIN_SHOWER
    1249 -> if (isDay) Condition.DAY_LIGHT_SLEET_SHOWERS else Condition.NIGHT_LIGHT_SLEET_SHOWERS
    1252 -> if (isDay) Condition.DAY_MODERATE_OR_HEAVY_SLEET_SHOWERS else Condition.NIGHT_MODERATE_OR_HEAVY_SLEET_SHOWERS
    1255 -> if (isDay) Condition.DAY_LIGHT_SNOW_SHOWERS else Condition.NIGHT_LIGHT_SNOW_SHOWERS
    1258 -> if (isDay) Condition.DAY_MODERATE_OR_HEAVY_SNOW_SHOWERS else Condition.NIGHT_MODERATE_OR_HEAVY_SNOW_SHOWERS
    1261 -> if (isDay) Condition.DAY_LIGHT_SHOWERS_OF_ICE_PELLETS else Condition.NIGHT_LIGHT_SHOWERS_OF_ICE_PELLETS
    1264 -> if (isDay) Condition.DAY_MODERATE_OR_HEAVY_SHOWERS_OF_ICE_PELLETS else Condition.NIGHT_MODERATE_OR_HEAVY_SHOWERS_OF_ICE_PELLETS
    1273 -> if (isDay) Condition.DAY_PATCHY_LIGHT_RAIN_WITH_THUNDER else Condition.NIGHT_PATCHY_LIGHT_RAIN_WITH_THUNDER
    1276 -> if (isDay) Condition.DAY_MODERATE_OR_HEAVY_RAIN_WITH_THUNDER else Condition.NIGHT_MODERATE_OR_HEAVY_RAIN_WITH_THUNDER
    1279 -> if (isDay) Condition.DAY_PATCHY_LIGHT_SNOW_WITH_THUNDER else Condition.NIGHT_PATCHY_LIGHT_SNOW_WITH_THUNDER
    1282 -> if (isDay) Condition.DAY_MODERATE_OR_HEAVY_SNOW_WITH_THUNDER else Condition.NIGHT_MODERATE_OR_HEAVY_SNOW_WITH_THUNDER
    else -> throw IllegalArgumentException()
}
