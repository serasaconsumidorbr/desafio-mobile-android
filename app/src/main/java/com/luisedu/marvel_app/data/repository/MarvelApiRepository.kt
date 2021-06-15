package com.luisedu.marvel_app.data.repository

import com.luisedu.marvel_app.data.retrofit.RetrofitService
import com.luisedu.marvel_app.model.MarvelApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MarvelApiRepository {

    private val serviceApi = RetrofitService.getService()
    private val ts = System.currentTimeMillis().toString()
    private val hash = getMd5(ts)

    suspend fun fetchCharactersList(listener: MarvelApiServiceListener) {
        return withContext(Dispatchers.Default) {
            serviceApi.fetchCharactersList(ts, hash, PUBLIC_KEY).enqueue(object :
                Callback<MarvelApiResponse> {

                override fun onResponse(call: Call<MarvelApiResponse>, response: Response<MarvelApiResponse>) {
                    response.body()?.let {
                        listener.onSuccess(it)
                    } ?: listener.onError(Exception())
                }

                override fun onFailure(call: Call<MarvelApiResponse>, t: Throwable) {
                    listener.onError(t)
                }
            })
        }
    }

    private fun getMd5(ts: String): String {
        try {
            val md = MessageDigest.getInstance(MD5)

            val messageDigest = md.digest(
                ts.toByteArray()
                        + PRIVATE_KEY.toByteArray()
                        + PUBLIC_KEY.toByteArray()
            )

            val no = BigInteger(SIGNUM_1, messageDigest)

            var hashtext = no.toString(RADIX)
            while (hashtext.length < HASH_TEXT_LENGTH) {
                hashtext = "0$hashtext"
            }
            return hashtext
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

    companion object {
        private const val PUBLIC_KEY = "b04a1f5aa28d3446ba69f723765a274d"
        private const val PRIVATE_KEY = "b9c4973fea6fc5d0d6a0dae4de27fbe7eaf3631b"

        private const val MD5 = "MD5"
        private const val SIGNUM_1 = 1
        private const val RADIX = 16
        private const val HASH_TEXT_LENGTH = 32
    }
}