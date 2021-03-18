package com.drawiin.myheroes.domain.util

interface ToDomainObject<T> {
    fun toDomainObject(): T
}