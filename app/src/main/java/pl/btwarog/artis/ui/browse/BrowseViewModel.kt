package pl.btwarog.artis.ui.browse

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.toFlow
import com.github.ajalt.timberkt.d
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import pl.btwarog.artis.ui.browse.BrowseScreenAction.NavigateToDetail
import pl.btwarog.brainz.data.remote.BrowseArtistsQuery
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.core_ui.presentation.model.ScreenAction
import pl.btwarog.core_ui.presentation.model.ScreenState
import pl.btwarog.core_ui.presentation.ui.BaseViewModel
import javax.inject.Inject

@ExperimentalCoroutinesApi
class BrowseViewModel @Inject constructor(
	private val apolloClient: ApolloClient,
	dispatcherExecutor: IDispatcherExecutor,
) : BaseViewModel<BrowseScreenState, BrowseScreenAction>(dispatcherExecutor) {

	init {
		fetchTemporaryArtists()
	}

	private fun fetchTemporaryArtists() = work {
		apolloClient.query(BrowseArtistsQuery("Nirvana", Input.fromNullable("YXJyYXljb25uZWN0aW9uOjE0"))).toFlow()
			.collect { response ->
				if (response.hasErrors()) {
					d { "Errors occurred" }
				} else {
					d { "Downloaded query" }
				}
			}
	}

	fun onArtistClicked(artistId: String) {
		processScreenAction(NavigateToDetail(artistId))
	}
}

sealed class BrowseScreenState : ScreenState

sealed class BrowseScreenAction : ScreenAction {
	class NavigateToDetail(val artistId: String) : BrowseScreenAction()
}