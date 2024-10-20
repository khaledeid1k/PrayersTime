package com.example.prayerstime.data.remote.utils

sealed class StandardErrors(val errorMassage : String) : Throwable(errorMassage) {
    object UnknownError : StandardErrors("Unknown Error")
    object InternetError : StandardErrors("Internet Error")
    object ServerTimeout : StandardErrors("Server Timeout")
    object BadRequest : StandardErrors("Bad Request")
}
