package pl.btwarog.artis.domain

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherExecutor {

	val workDispatcher: CoroutineDispatcher
	val resultDispatcher: CoroutineDispatcher
}