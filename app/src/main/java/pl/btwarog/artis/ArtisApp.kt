package pl.btwarog.artis

import android.app.Application
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.artis.injection.AppComponent
import pl.btwarog.artis.injection.DaggerAppComponent
import javax.inject.Inject

class ArtisApp : Application() {

	@Inject
	lateinit var dispatcherExecutor: IDispatcherExecutor

	val appComponent: AppComponent by lazy {
		DaggerAppComponent
			.factory()
			.create(this)
	}

	override fun onCreate() {
		super.onCreate()
		appComponent.inject(this)
	}
}