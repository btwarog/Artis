package pl.btwarog.artis.ui.browse

import pl.btwarog.artis.ui.browse.BrowseScreenAction.NavigateToDetail
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.core_ui.presentation.model.ScreenAction
import pl.btwarog.core_ui.presentation.model.ScreenState
import pl.btwarog.core_ui.presentation.ui.BaseViewModel
import javax.inject.Inject

class BrowseViewModel @Inject constructor(
	dispatcherExecutor: IDispatcherExecutor,
) : BaseViewModel<BrowseScreenState, BrowseScreenAction>(dispatcherExecutor) {

	fun onArtistClicked(artistId: String) {
		processScreenAction(NavigateToDetail(artistId))
	}
}

sealed class BrowseScreenState : ScreenState

sealed class BrowseScreenAction : ScreenAction {
	class NavigateToDetail(val artistId: String) : BrowseScreenAction()
}