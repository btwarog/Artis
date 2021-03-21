package pl.btwarog.artis.ui.bookmarks

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pl.btwarog.artis.ui.bookmarks.BookmarksScreenAction.BookmarkActionFailed
import pl.btwarog.artis.ui.bookmarks.BookmarksScreenAction.BookmarkActionFinished
import pl.btwarog.artis.ui.bookmarks.BookmarksScreenAction.BookmarkActionLoading
import pl.btwarog.artis.ui.bookmarks.BookmarksScreenAction.NavigateToDetail
import pl.btwarog.artis.ui.bookmarks.BookmarksScreenState.DataEmpty
import pl.btwarog.artis.ui.bookmarks.BookmarksScreenState.DataLoaded
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.brainz.domain.usecase.GetBookmarkedArtistsListUseCase
import pl.btwarog.brainz.domain.usecase.UnbookmarkArtistUseCase
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.core_ui.presentation.model.ScreenAction
import pl.btwarog.core_ui.presentation.model.ScreenState
import pl.btwarog.core_ui.presentation.ui.BaseViewModel
import javax.inject.Inject

class BookmarksViewModel @Inject constructor(
	private val getBookmarkedArtistsListUseCase: GetBookmarkedArtistsListUseCase,
	private val unbookmarkArtistUseCase: UnbookmarkArtistUseCase,
	dispatcherExecutor: IDispatcherExecutor
) : BaseViewModel<BookmarksScreenState, BookmarksScreenAction>(dispatcherExecutor) {

	init {
		getBookmarkedArtists()
	}

	private fun getBookmarkedArtists() = work {
		getBookmarkedArtistsListUseCase.getBookmarkedArtists().collect { list ->
			processScreenState(
				if (list.isEmpty()) {
					DataEmpty
				} else {
					DataLoaded(list)
				}
			)
		}
	}

	fun onArtistClicked(artistId: String) {
		processScreenAction(NavigateToDetail(artistId))
	}

	fun onArtistBookmarkClicked(id: String) {
		viewModelScope.launch(dispatcherExecutor.workDispatcher) {
			processScreenAction(BookmarkActionLoading)
			try {
				unbookmarkArtistUseCase.unbookmarkArtist(id)
				processScreenAction(BookmarkActionFinished)
			} catch (exception: Exception) {
				processScreenAction(BookmarkActionFailed)
			}
		}
	}
}

sealed class BookmarksScreenState : ScreenState {
	object DataEmpty : BookmarksScreenState()
	class DataLoaded(val data: List<ArtistDetailInfo>) : BookmarksScreenState()
}

sealed class BookmarksScreenAction : ScreenAction {
	class NavigateToDetail(val artistId: String) : BookmarksScreenAction()
	object BookmarkActionFinished : BookmarksScreenAction()
	object BookmarkActionLoading : BookmarksScreenAction()
	object BookmarkActionFailed : BookmarksScreenAction()
}