package pl.btwarog.artis.injection

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.multibindings.Multibinds
import pl.btwarog.core.presentation.ui.BaseViewModelFactory

@Module
abstract class ViewModelsModule {

	@Multibinds
	abstract fun viewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>

	@Multibinds
	abstract fun assistedViewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards BaseViewModelFactory<out ViewModel>>
}