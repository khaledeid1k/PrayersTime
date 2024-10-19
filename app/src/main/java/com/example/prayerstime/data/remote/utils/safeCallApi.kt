package com.example.prayerstime.data.remote.utils

import com.example.prayerstime.data.remote.BasicApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.io.InterruptedIOException


suspend fun <T> safeCallApi(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    call: suspend () -> BasicApiResponse<T>
): T? {

    return  withContext(dispatcher) {
        try {
            val response = call()
             response.data
        }
        catch (e: IOException) {
            if (e is InterruptedIOException) {
                throw StandardErrors.ServerTimeout
            } else {
                throw StandardErrors.InternetError
            }
        } catch (e: HttpException) {
            if (e.code() == HttpExceptionCode.NotFound.code) {
                throw StandardErrors.BadRequest
            } else {
                throw StandardErrors.UnknownError

            }

        } catch (e: Exception) {
            throw StandardErrors.UnknownError

        }
    }
}
