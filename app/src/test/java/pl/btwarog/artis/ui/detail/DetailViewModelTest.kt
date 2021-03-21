package pl.btwarog.artis.ui.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.*
import pl.btwarog.artis.utils.ArtistsDataFactory
import pl.btwarog.brainz.domain.error.ResultWrapper
import pl.btwarog.brainz.domain.usecase.BookmarkArtistUseCase
import pl.btwarog.brainz.domain.usecase.GetArtistDetailUseCase
import pl.btwarog.brainz.domain.usecase.UnbookmarkArtistUseCase
import pl.btwarog.core.domain.executors.IDispatcherExecutor

class DetailViewModelTest {

	private val testCoroutineDispatcher: TestCoroutineDispatcher
		get() {
			return TestCoroutineDispatcher()
		}

	private val savedStateHandle: SavedStateHandle = mockk()
	private val dispatcherExecutor: IDispatcherExecutor = mockk()
	private val getArtistDetailUseCase: GetArtistDetailUseCase = mockk()
	private val bookmarkArtistUseCase: BookmarkArtistUseCase = mockk()
	private val unbookmarkArtistUseCase: UnbookmarkArtistUseCase = mockk()
	private val artistId: String = ArtistsDataFactory.getArtistDetailInfoId()

	private lateinit var detailViewModel: DetailViewModel

	init {
		coEvery { dispatcherExecutor.workDispatcher } returns testCoroutineDispatcher
		coEvery { dispatcherExecutor.resultDispatcher } returns testCoroutineDispatcher
		every { savedStateHandle.get<String>(ARG_DETAIL_ARTIST_ID) } returns artistId
	}

	@Before
	fun setup() {
		Dispatchers.setMain(testCoroutineDispatcher)
	}

	@Test
	fun `given success when artist detail gets fetched then DataLoaded is returned`() =
		testCoroutineDispatcher.runBlockingTest {
			val artist = ArtistsDataFactory.getArtistDetailInfo(false)
			coEvery { getArtistDetailUseCase.getArtistDetail(artistId) } returns ResultWrapper.Success(artist)
			initViewModel()
			detailViewModel.screenState.test {
				assertTrue(expectItem() is DetailScreenState.DataLoaded)
			}
		}

	@Test
	fun `given network error when artist detail gets fetched then DataError with networkError is returned`() =
		testCoroutineDispatcher.runBlockingTest {
			coEvery { getArtistDetailUseCase.getArtistDetail(artistId) } returns ResultWrapper.NetworkError
			initViewModel()
			detailViewModel.screenState.test {
				expectItem()?.let { item ->
					assert(item is DetailScreenState.DataError)
					assert((item as DetailScreenState.DataError).networkError)
				}
			}
		}

	@Test
	fun `given general error when artist detail gets fetched then DataError without networkError is returned`() =
		testCoroutineDispatcher.runBlockingTest {
			coEvery { getArtistDetailUseCase.getArtistDetail(artistId) } returns ResultWrapper.GeneralError
			initViewModel()
			detailViewModel.screenState.test {
				expectItem()?.let { item ->
					assert(item is DetailScreenState.DataError)
					assertNotEquals((item as DetailScreenState.DataError).networkError, true)
				}
			}
		}

	private fun initViewModel() {
		detailViewModel = DetailViewModel(
			dispatcherExecutor,
			getArtistDetailUseCase,
			unbookmarkArtistUseCase,
			bookmarkArtistUseCase,
			savedStateHandle
		)
	}

	@After
	fun cleanUp() {
		testCoroutineDispatcher.cleanupTestCoroutines()
		Dispatchers.resetMain()
	}
}