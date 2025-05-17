package com.interview.ivanjfbr.core.domain

abstract class BaseUseCase<in Params, out T> {
    abstract suspend fun execute(params: Params): T
}