package com.ez.weather4you.data.local.forecast.room.annotations


@RequiresOptIn(
    level = RequiresOptIn.Level.WARNING,
    message = "DO NOT USE THIS FUNCTION. Use functions annotated with @SavedLocationAPI or @CurrentLocationAPI"
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class RestrictedDaoApi