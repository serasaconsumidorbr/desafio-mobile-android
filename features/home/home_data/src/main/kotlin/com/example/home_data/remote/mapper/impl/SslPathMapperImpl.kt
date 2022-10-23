package com.example.home_data.remote.mapper.impl

import com.example.home_data.remote.mapper.SslPathMapper

class SslPathMapperImpl: SslPathMapper {

    companion object {
        private const val HTTP_PREFIX = "http"
        private const val HTTPS_PREFIX = "https"
    }

    override fun map(path: String?): String? = path?.replace(HTTP_PREFIX, HTTPS_PREFIX)
}
