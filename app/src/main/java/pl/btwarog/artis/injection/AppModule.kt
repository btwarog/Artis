package pl.btwarog.artis.injection

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import pl.btwarog.artis.domain.DispatcherExecutorImpl
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import javax.inject.Singleton

@Module
object AppModule {

	@Singleton
	@Provides
	fun provideContext(application: Application): Context {
		return application.applicationContext
	}

	@Singleton
	@Provides
	fun provideDispatcherExecutor(): IDispatcherExecutor {
		return DispatcherExecutorImpl(Dispatchers.IO, Dispatchers.Main)
	}
}
