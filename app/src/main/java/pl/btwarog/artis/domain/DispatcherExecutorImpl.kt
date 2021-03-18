package pl.btwarog.artis.domain

import kotlinx.coroutines.CoroutineDispatcher
import pl.btwarog.core.domain.executors.IDispatcherExecutor

class DispatcherExecutorImpl(
	override val workDispatcher: CoroutineDispatcher,
	override val resultDispatcher: CoroutineDispatcher
) : IDispatcherExecutor