package com.vtech.mobile.compose_weather.network

import androidx.annotation.WorkerThread
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.Closeable
import java.io.IOException
import java.util.concurrent.TimeUnit

class NetworkManagerImpl : NetworkManager {

    @WorkerThread
    override fun get(url: String): String? {
        val okHttpRequestBuilder = okhttp3.Request.Builder()
            .url(url)
            .get()
        var okHttpResponse: Response? = null
        var body: ResponseBody? = null
        return try {
            okHttpResponse = okHttpClient.value.newCall(okHttpRequestBuilder.build()).execute()
            body = okHttpResponse.body()
            body?.string()
        } catch (e: IOException) {
            throw IllegalStateException(e)
        } finally {
            closeSilently(body, okHttpResponse)
        }
    }

    companion object {
        private val okHttpClient = lazy {
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(15, TimeUnit.SECONDS)
            builder.build()
        }

        private fun closeSilently(vararg xs: Closeable?) {
            for (x in xs) {
                try {
                    x?.close()
                } catch (ignored: Throwable) {

                }
            }
        }
    }
}