package dev.ribeiro.bruno.desafioandroid.core

abstract class UseCase<Source> {
    abstract suspend fun execute(): Source
}