package pl.btwarog.brainz.domain.error

import com.apollographql.apollo.exception.ApolloNetworkException

class NetworkException : Exception()
class GeneralException : Exception()

sealed class ResultWrapper<out T> {
	data class Success<out T>(val value: T) : ResultWrapper<T>()
	object GeneralError : ResultWrapper<Nothing>()
	object NetworkError : ResultWrapper<Nothing>()
}

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T> {
	return try {
		ResultWrapper.Success(apiCall.invoke())
	} catch (throwable: Throwable) {
		when (throwable) {
			is ApolloNetworkException -> ResultWrapper.NetworkError
			else -> ResultWrapper.GeneralError
		}
	}
}