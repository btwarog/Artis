package pl.btwarog.artis.ui.browse

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.btwarog.brainz.presentation.paging.factory.ArtistListDataFactory
import pl.btwarog.artis.ui.browse.BrowseScreenAction.NavigateToDetail
import pl.btwarog.artis.ui.browse.BrowseScreenState.ArtistsListDataLoaded
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.core_ui.presentation.model.ScreenAction
import pl.btwarog.core_ui.presentation.model.ScreenState
import pl.btwarog.core_ui.presentation.ui.BaseViewModel
import javax.inject.Inject

@ExperimentalCoroutinesApi
class BrowseViewModel @Inject constructor(
	private val artistListDataFactory: ArtistListDataFactory,
	dispatcherExecutor: IDispatcherExecutor,
) : BaseViewModel<BrowseScreenState, BrowseScreenAction>(dispatcherExecutor) {

	var pagingJob: Job? = null

	init {
		pagingJob = viewModelScope.launch {
			artistListDataFactory.create("Nirv").cachedIn(viewModelScope).collect { data ->
				withContext(dispatcherExecutor.resultDispatcher) {
					processScreenState(ArtistsListDataLoaded(data))
				}
			}
		}
	}

	fun onArtistClicked(artistId: String) {
		processScreenAction(NavigateToDetail(artistId))
	}
}

sealed class BrowseScreenState : ScreenState {
	class ArtistsListDataLoaded(val pagingData: PagingData<ArtistBasicInfo>) : BrowseScreenState()
}

sealed class BrowseScreenAction : ScreenAction {
	class NavigateToDetail(val artistId: String) : BrowseScreenAction()
}