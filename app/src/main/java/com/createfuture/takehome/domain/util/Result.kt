package com.createfuture.takehome.domain.util

/**
 * This is will a generic result. Which used for all types of API response.
 * We can re-use this class in different projects.
 * If we have a multi module architecture. We can move this to network module and re-use it in other project.
 */

typealias RootError = Error

sealed interface Result<out D, out E> {

    data class Success<out D, out E : RootError>(val data: D?) : Result<D, E>
    data class Failure<out D, out E : RootError>(val error: E) : Result<D, E>
}