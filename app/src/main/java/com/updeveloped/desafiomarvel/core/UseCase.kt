package com.updeveloped.desafiomarvel.core

abstract class UseCase<Source> {
    abstract suspend fun execute(): Source
}