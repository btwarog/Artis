package pl.btwarog.core.domain.executors

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherExecutor {

	val workDispatcher: CoroutineDispatcher
	val resultDispatcher: CoroutineDispatcher
}