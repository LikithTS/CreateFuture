package com.createfuture.takehome.domain.util

/**
 * We will create one of the error for this project. Later we can enhance to different error type
 * Like : validation error, payment error, login error etc.
 *
 * Here I have handled only few Network errors. We can add support for
 * Bad Request
 * Un-Authorised
 * etc
 */
sealed interface NetworkApiErrors : Error {

    object FAILURE: NetworkApiErrors

    object NOT_FOUND: NetworkApiErrors

    object UN_AUTHORISED: NetworkApiErrors

    data class GenericException(val message: String): NetworkApiErrors
}