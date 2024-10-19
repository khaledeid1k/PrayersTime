package com.example.prayerstime.data.remote.utils
sealed class HttpExceptionCode(val code: Int) {

    object NotFound : HttpExceptionCode(404)

}
