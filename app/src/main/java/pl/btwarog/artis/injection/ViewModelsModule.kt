package pl.btwarog.artis.injection

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds
import pl.btwarog.artis.ui.bookmarks.BookmarksViewModel
import pl.btwarog.artis.ui.browse.BrowseViewModel
import pl.btwarog.artis.ui.detail.DetailViewModel
import pl.btwarog.core_ui.injection.ViewModelKey
import pl.btwarog.core_ui.presentation.ui.BaseViewModelFactory

@Module
abstract class ViewModelsModule {

	@Multibinds
	abstract fun viewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>

	@Multibinds
	abstract fun assistedViewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards BaseViewModelFactory<out ViewModel>>

	@Binds
	@IntoMap
	@ViewModelKey(BookmarksViewModel::class)
	abstract fun bindBookmarksViewModel(viewModel: BookmarksViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(BrowseViewModel::class)
	abstract fun bindBrowseViewModel(viewModel: BrowseViewModel.Factory): BaseViewModelFactory<out ViewModel>

	@Binds
	@IntoMap
	@ViewModelKey(DetailViewModel::class)
	abstract fun bindDetailVMFactory(factory: DetailViewModel.Factory): BaseViewModelFactory<out ViewModel>
}