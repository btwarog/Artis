package pl.btwarog.artis

import android.app.Application
import androidx.fragment.app.Fragment
import pl.btwarog.artis.injection.AppComponent
import pl.btwarog.artis.injection.DaggerAppComponent
import timber.log.Timber

class ArtisApp : Application() {

	val appComponent: AppComponent by lazy {
		DaggerAppComponent
			.factory()
			.create(this)
	}

	override fun onCreate() {
		super.onCreate()
		appComponent.inject(this)
		if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
	}
}

val Fragment.appComponent get() = (requireActivity().application as ArtisApp).appComponent
