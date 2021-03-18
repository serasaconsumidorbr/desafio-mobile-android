package com.drawiin.myheroes.domain.util

interface FromDomainObject<T, DomainObject> {
    fun fromDomainObject(domainModel: DomainObject): T
}