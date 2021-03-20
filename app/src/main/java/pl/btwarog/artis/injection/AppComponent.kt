package pl.btwarog.artis.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import pl.btwarog.artis.ArtisApp
import pl.btwarog.artis.ui.bookmarks.BookmarksScreen
import pl.btwarog.artis.ui.browse.BrowseScreen
import pl.btwarog.artis.ui.detail.DetailScreen
import pl.btwarog.artis.ui.splash.SplashScreen
import pl.btwarog.brainz.injection.BrainzDataModule
import pl.btwarog.brainz.injection.BrainzNetworkModule
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		AppModule::class,
		BrainzNetworkModule::class,
		BrainzDataModule::class,
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
	fun inject(BrowseScreen: BrowseScreen)
	fun inject(bookmarksScreen: BookmarksScreen)
	fun inject(detailFragment: DetailScreen)
}