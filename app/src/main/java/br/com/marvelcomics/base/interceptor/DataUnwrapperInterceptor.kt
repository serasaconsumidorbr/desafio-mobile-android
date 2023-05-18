package br.com.marvelcomics.base.interceptor

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset

class DataUnwrapperInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!response.isSuccessful) return response

        val responseBody = convertResponseToString(response.body)

        responseBody?.let {
            val json = try {
                JSONObject(it)
            } catch (e: Exception) {
                return response
            }
            val contentType = response.body?.contentType()

            if (!json.has("data")) return response

            if (json.get("data") is JSONObject) {
                val body = buildNewResponseBody(json.getJSONObject("data"), contentType)
                return response.newBuilder().body(body).build()
            }

            if (json.get("data") is JSONArray) {
                val body = buildNewResponseBody(json.getJSONArray("data"), contentType)
                return response.newBuilder().body(body).build()
            }
        }
        return response
    }

    private fun <T> buildNewResponseBody(json: T, contentType: MediaType?): ResponseBody {
        return json.toString().toResponseBody(contentType)
    }

    private fun convertResponseToString(response: ResponseBody?): String? {
        val source = response?.source()
        source?.request(Long.MAX_VALUE)
        val buffer = source?.buffer
        return buffer?.clone()?.readString(Charset.forName("UTF-8"))
    }

}