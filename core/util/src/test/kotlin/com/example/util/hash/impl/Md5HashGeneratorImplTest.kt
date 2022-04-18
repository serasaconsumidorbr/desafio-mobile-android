package com.example.util.hash.impl

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class Md5HashGeneratorImplTest {
    private val hashGenerator = Md5HashGeneratorImpl()
    @Test
    fun hash_validData_returnsValidString() {
        assertThat(
            hashGenerator(
                timestamp = 1,
                publicKey = "1234",
                privateKey = "abcd"
            ),
        ).isEqualTo("ffd275c5130566a2916217b101f26150")
    }
}