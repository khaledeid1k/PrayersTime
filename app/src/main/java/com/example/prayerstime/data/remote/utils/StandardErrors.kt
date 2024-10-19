package com.example.prayerstime.data.remote.utils

sealed class StandardErrors : Throwable() {
    object UnknownError : StandardErrors()
    object InternetError : StandardErrors()
    object ServerTimeout : StandardErrors()
    object BadRequest : StandardErrors()
}
