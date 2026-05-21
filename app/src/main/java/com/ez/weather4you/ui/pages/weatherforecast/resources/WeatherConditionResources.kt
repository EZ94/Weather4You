package com.ez.weather4you.ui.pages.weatherforecast.resources

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.ColorScheme
import com.ez.weather4you.R
import com.ez.weather4you.domain.entity.forecast.Condition
import com.ez.weather4you.ui.theme.colorscheme.DayCloudyColorScheme
import com.ez.weather4you.ui.theme.colorscheme.DaySunnyColorScheme
import com.ez.weather4you.ui.theme.colorscheme.NightClearColorScheme
import com.ez.weather4you.ui.theme.colorscheme.NightCloudyColorScheme

data class WeatherConditionResources(
    @field:DrawableRes val icon: Int,
    @field:DrawableRes val backgroundImage: Int,
    @field:StringRes val text: Int,
    val colorScheme: ColorScheme
) {
    companion object {
        fun associatedWith(condition: Condition): WeatherConditionResources = when (condition) {
            Condition.DAY_SUNNY -> WeatherConditionResources(
                icon = R.drawable.day_113,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1000,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_CLEAR -> WeatherConditionResources(
                icon = R.drawable.night_113,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1000,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_PARTLY_CLOUDY -> WeatherConditionResources(
                icon = R.drawable.day_116,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1003,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_PARTLY_CLOUDY -> WeatherConditionResources(
                icon = R.drawable.night_116,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1003,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_CLOUDY -> WeatherConditionResources(
                icon = R.drawable.day_119,
                backgroundImage = R.drawable.background_day_cloudy,
                text = R.string.day_1006,
                colorScheme = DayCloudyColorScheme
            )

            Condition.NIGHT_CLOUDY -> WeatherConditionResources(
                icon = R.drawable.night_119,
                backgroundImage = R.drawable.background_night_cloudy,
                text = R.string.night_1006,
                colorScheme = NightCloudyColorScheme
            )

            Condition.DAY_OVERCAST -> WeatherConditionResources(
                icon = R.drawable.day_122,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1009,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_OVERCAST -> WeatherConditionResources(
                icon = R.drawable.night_122,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1009,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_MIST -> WeatherConditionResources(
                icon = R.drawable.day_143,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1030,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_MIST -> WeatherConditionResources(
                icon = R.drawable.night_143,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1030,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_PATCHY_RAIN_POSSIBLE -> WeatherConditionResources(
                icon = R.drawable.day_176,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1063,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_PATCHY_RAIN_POSSIBLE -> WeatherConditionResources(
                icon = R.drawable.night_176,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1063,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_PATCHY_SNOW_POSSIBLE -> WeatherConditionResources(
                icon = R.drawable.day_179,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1066,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_PATCHY_SNOW_POSSIBLE -> WeatherConditionResources(
                icon = R.drawable.night_179,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1066,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_PATCHY_SLEET_POSSIBLE -> WeatherConditionResources(
                icon = R.drawable.day_182,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1069,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_PATCHY_SLEET_POSSIBLE -> WeatherConditionResources(
                icon = R.drawable.night_182,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1069,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_PATCHY_FREEZING_DRIZZLE_POSSIBLE -> WeatherConditionResources(
                icon = R.drawable.day_185,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1072,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_PATCHY_FREEZING_DRIZZLE_POSSIBLE -> WeatherConditionResources(
                icon = R.drawable.night_185,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1072,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_THUNDERY_OUTBREAKS_POSSIBLE -> WeatherConditionResources(
                icon = R.drawable.day_200,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1087,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_THUNDERY_OUTBREAKS_POSSIBLE -> WeatherConditionResources(
                icon = R.drawable.night_200,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1087,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_BLOWING_SNOW -> WeatherConditionResources(
                icon = R.drawable.day_227,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1114,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_BLOWING_SNOW -> WeatherConditionResources(
                icon = R.drawable.night_227,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1114,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_BLIZZARD -> WeatherConditionResources(
                icon = R.drawable.day_230,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1117,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_BLIZZARD -> WeatherConditionResources(
                icon = R.drawable.night_230,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1117,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_FOG -> WeatherConditionResources(
                icon = R.drawable.day_248,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1135,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_FOG -> WeatherConditionResources(
                icon = R.drawable.night_248,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1135,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_FREEZING_FOG -> WeatherConditionResources(
                icon = R.drawable.day_260,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1147,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_FREEZING_FOG -> WeatherConditionResources(
                icon = R.drawable.night_260,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1147,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_PATCHY_LIGHT_DRIZZLE -> WeatherConditionResources(
                icon = R.drawable.day_263,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1150,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_PATCHY_LIGHT_DRIZZLE -> WeatherConditionResources(
                icon = R.drawable.night_263,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1150,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_LIGHT_DRIZZLE -> WeatherConditionResources(
                icon = R.drawable.day_266,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1153,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_LIGHT_DRIZZLE -> WeatherConditionResources(
                icon = R.drawable.night_266,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1153,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_FREEZING_DRIZZLE -> WeatherConditionResources(
                icon = R.drawable.day_281,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1168,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_FREEZING_DRIZZLE -> WeatherConditionResources(
                icon = R.drawable.night_281,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1168,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_HEAVY_FREEZING_DRIZZLE -> WeatherConditionResources(
                icon = R.drawable.day_284,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1171,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_HEAVY_FREEZING_DRIZZLE -> WeatherConditionResources(
                icon = R.drawable.night_284,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1171,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_PATCHY_LIGHT_RAIN -> WeatherConditionResources(
                icon = R.drawable.day_293,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1180,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_PATCHY_LIGHT_RAIN -> WeatherConditionResources(
                icon = R.drawable.night_293,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1180,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_LIGHT_RAIN -> WeatherConditionResources(
                icon = R.drawable.day_296,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1183,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_LIGHT_RAIN -> WeatherConditionResources(
                icon = R.drawable.night_296,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1183,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_MODERATE_RAIN_AT_TIMES -> WeatherConditionResources(
                icon = R.drawable.day_299,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1186,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_MODERATE_RAIN_AT_TIMES -> WeatherConditionResources(
                icon = R.drawable.night_299,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1186,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_MODERATE_RAIN -> WeatherConditionResources(
                icon = R.drawable.day_302,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1189,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_MODERATE_RAIN -> WeatherConditionResources(
                icon = R.drawable.night_302,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1189,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_HEAVY_RAIN_AT_TIMES -> WeatherConditionResources(
                icon = R.drawable.day_305,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1192,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_HEAVY_RAIN_AT_TIMES -> WeatherConditionResources(
                icon = R.drawable.night_305,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1192,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_HEAVY_RAIN -> WeatherConditionResources(
                icon = R.drawable.day_308,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1195,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_HEAVY_RAIN -> WeatherConditionResources(
                icon = R.drawable.night_308,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1195,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_LIGHT_FREEZING_RAIN -> WeatherConditionResources(
                icon = R.drawable.day_311,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1198,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_LIGHT_FREEZING_RAIN -> WeatherConditionResources(
                icon = R.drawable.night_311,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1198,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_MODERATE_OR_HEAVY_FREEZING_RAIN -> WeatherConditionResources(
                icon = R.drawable.day_314,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1201,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_MODERATE_OR_HEAVY_FREEZING_RAIN -> WeatherConditionResources(
                icon = R.drawable.night_314,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1201,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_LIGHT_SLEET -> WeatherConditionResources(
                icon = R.drawable.day_317,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1204,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_LIGHT_SLEET -> WeatherConditionResources(
                icon = R.drawable.night_317,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1204,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_MODERATE_OR_HEAVY_SLEET -> WeatherConditionResources(
                icon = R.drawable.day_320,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1207,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_MODERATE_OR_HEAVY_SLEET -> WeatherConditionResources(
                icon = R.drawable.night_320,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1207,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_PATCHY_LIGHT_SNOW -> WeatherConditionResources(
                icon = R.drawable.day_323,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1210,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_PATCHY_LIGHT_SNOW -> WeatherConditionResources(
                icon = R.drawable.night_323,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1210,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_LIGHT_SNOW -> WeatherConditionResources(
                icon = R.drawable.day_326,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1213,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_LIGHT_SNOW -> WeatherConditionResources(
                icon = R.drawable.night_326,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1213,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_PATCHY_MODERATE_SNOW -> WeatherConditionResources(
                icon = R.drawable.day_329,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1216,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_PATCHY_MODERATE_SNOW -> WeatherConditionResources(
                icon = R.drawable.night_329,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1216,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_MODERATE_SNOW -> WeatherConditionResources(
                icon = R.drawable.day_332,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1219,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_MODERATE_SNOW -> WeatherConditionResources(
                icon = R.drawable.night_332,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1219,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_PATCHY_HEAVY_SNOW -> WeatherConditionResources(
                icon = R.drawable.day_335,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1222,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_PATCHY_HEAVY_SNOW -> WeatherConditionResources(
                icon = R.drawable.night_335,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1222,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_HEAVY_SNOW -> WeatherConditionResources(
                icon = R.drawable.day_338,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1225,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_HEAVY_SNOW -> WeatherConditionResources(
                icon = R.drawable.night_338,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1225,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_ICE_PELLETS -> WeatherConditionResources(
                icon = R.drawable.day_350,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1237,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_ICE_PELLETS -> WeatherConditionResources(
                icon = R.drawable.night_350,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1237,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_LIGHT_RAIN_SHOWER -> WeatherConditionResources(
                icon = R.drawable.day_353,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1240,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_LIGHT_RAIN_SHOWER -> WeatherConditionResources(
                icon = R.drawable.night_353,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1240,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_MODERATE_OR_HEAVY_RAIN_SHOWER -> WeatherConditionResources(
                icon = R.drawable.day_356,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1243,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_MODERATE_OR_HEAVY_RAIN_SHOWER -> WeatherConditionResources(
                icon = R.drawable.night_356,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1243,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_TORRENTIAL_RAIN_SHOWER -> WeatherConditionResources(
                icon = R.drawable.day_359,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1246,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_TORRENTIAL_RAIN_SHOWER -> WeatherConditionResources(
                icon = R.drawable.night_359,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1246,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_LIGHT_SLEET_SHOWERS -> WeatherConditionResources(
                icon = R.drawable.day_362,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1249,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_LIGHT_SLEET_SHOWERS -> WeatherConditionResources(
                icon = R.drawable.night_362,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1249,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_MODERATE_OR_HEAVY_SLEET_SHOWERS -> WeatherConditionResources(
                icon = R.drawable.day_365,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1252,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_MODERATE_OR_HEAVY_SLEET_SHOWERS -> WeatherConditionResources(
                icon = R.drawable.night_365,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1252,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_LIGHT_SNOW_SHOWERS -> WeatherConditionResources(
                icon = R.drawable.day_368,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1255,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_LIGHT_SNOW_SHOWERS -> WeatherConditionResources(
                icon = R.drawable.night_368,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1255,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_MODERATE_OR_HEAVY_SNOW_SHOWERS -> WeatherConditionResources(
                icon = R.drawable.day_371,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1258,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_MODERATE_OR_HEAVY_SNOW_SHOWERS -> WeatherConditionResources(
                icon = R.drawable.night_371,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1258,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_LIGHT_SHOWERS_OF_ICE_PELLETS -> WeatherConditionResources(
                icon = R.drawable.day_374,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1261,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_LIGHT_SHOWERS_OF_ICE_PELLETS -> WeatherConditionResources(
                icon = R.drawable.night_374,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1261,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_MODERATE_OR_HEAVY_SHOWERS_OF_ICE_PELLETS -> WeatherConditionResources(
                icon = R.drawable.day_377,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1264,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_MODERATE_OR_HEAVY_SHOWERS_OF_ICE_PELLETS -> WeatherConditionResources(
                icon = R.drawable.night_377,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1264,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_PATCHY_LIGHT_RAIN_WITH_THUNDER -> WeatherConditionResources(
                icon = R.drawable.day_386,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1273,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_PATCHY_LIGHT_RAIN_WITH_THUNDER -> WeatherConditionResources(
                icon = R.drawable.night_386,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1273,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_MODERATE_OR_HEAVY_RAIN_WITH_THUNDER -> WeatherConditionResources(
                icon = R.drawable.day_389,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1276,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_MODERATE_OR_HEAVY_RAIN_WITH_THUNDER -> WeatherConditionResources(
                icon = R.drawable.night_389,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1276,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_PATCHY_LIGHT_SNOW_WITH_THUNDER -> WeatherConditionResources(
                icon = R.drawable.day_392,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1279,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_PATCHY_LIGHT_SNOW_WITH_THUNDER -> WeatherConditionResources(
                icon = R.drawable.night_392,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1279,
                colorScheme = NightClearColorScheme
            )

            Condition.DAY_MODERATE_OR_HEAVY_SNOW_WITH_THUNDER -> WeatherConditionResources(
                icon = R.drawable.day_395,
                backgroundImage = R.drawable.background_day_sunny,
                text = R.string.day_1282,
                colorScheme = DaySunnyColorScheme
            )

            Condition.NIGHT_MODERATE_OR_HEAVY_SNOW_WITH_THUNDER -> WeatherConditionResources(
                icon = R.drawable.night_395,
                backgroundImage = R.drawable.background_night_clear,
                text = R.string.night_1282,
                colorScheme = NightClearColorScheme
            )
        }
    }
}
