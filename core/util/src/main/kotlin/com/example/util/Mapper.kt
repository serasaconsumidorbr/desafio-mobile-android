package com.example.util

interface Mapper<in INPUT, out OUTPUT> {
    fun mapFrom(from: INPUT): OUTPUT
}