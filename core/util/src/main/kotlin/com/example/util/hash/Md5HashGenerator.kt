package com.example.util.hash

interface Md5HashGenerator {
    operator fun invoke(
        timestamp: Long,
        publicKey: String,
        privateKey: String
    ): String
}