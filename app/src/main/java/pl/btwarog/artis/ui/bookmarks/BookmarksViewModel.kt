package pl.btwarog.artis.ui.bookmarks

import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.core.presentation.model.ScreenAction
import pl.btwarog.core.presentation.model.ScreenState
import pl.btwarog.core.presentation.ui.BaseViewModel
import javax.inject.Inject

class BookmarksViewModel @Inject constructor(
	dispatcherExecutor: IDispatcherExecutor,
) : BaseViewModel<BookmarksScreenState, BookmarksScreenAction>(dispatcherExecutor)

sealed class BookmarksScreenState : ScreenState

sealed class BookmarksScreenAction : ScreenAction