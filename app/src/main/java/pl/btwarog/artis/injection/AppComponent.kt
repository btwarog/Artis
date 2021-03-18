package pl.btwarog.artis.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import pl.btwarog.artis.ArtisApp
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		AppModule::class,
		ViewModelsModule::class,
	]
)
interface AppComponent {

	@Component.Factory
	interface Factory {

		fun create(
			@BindsInstance
			application: Application
		): AppComponent
	}

	fun inject(artisApp: ArtisApp)
}