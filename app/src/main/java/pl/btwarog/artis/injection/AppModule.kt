package pl.btwarog.artis.injection

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import pl.btwarog.artis.BuildConfig
import pl.btwarog.artis.domain.DispatcherExecutorImpl
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.core.injection.LOG_ENABLED_KEY
import javax.inject.Named
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

	@Singleton
	@Named(LOG_ENABLED_KEY)
	@Provides
	fun provideLogEnabledFlag(): Boolean {
		return BuildConfig.HTTP_LOG_ENABLE
	}
}
