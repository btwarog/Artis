package pl.btwarog.artis

import kotlinx.coroutines.CoroutineDispatcher
import pl.btwarog.artis.domain.IDispatcherExecutor

class DispatcherExecutorImpl(
	override val workDispatcher: CoroutineDispatcher,
	override val resultDispatcher: CoroutineDispatcher
) : IDispatcherExecutor