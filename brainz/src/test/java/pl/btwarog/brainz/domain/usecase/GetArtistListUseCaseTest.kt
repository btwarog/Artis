package pl.btwarog.brainz.domain.usecase

import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions
import org.junit.*
import pl.btwarog.brainz.domain.repository.IArtistsRepository
import pl.btwarog.brainz.domain.util.ArtistsDataFactory

class GetArtistListUseCaseTest {

	private val testCoroutineDispatcher: TestCoroutineDispatcher
		get() {
			return TestCoroutineDispatcher()
		}

	private val artistRepository: IArtistsRepository = mockk()

	private val getArtistsListUseCase = GetArtistsListUseCase(artistRepository)

	private val expectedData = ArtistsDataFactory.getPaginateListDomain()

	init {
		coEvery { artistRepository.getArtists(any(), any(), any()) } returns expectedData
	}

	@Before
	fun setup() {
		Dispatchers.setMain(testCoroutineDispatcher)
	}

	@Test
	fun `given parameters get called the repository gets called once`() = testCoroutineDispatcher.runBlockingTest {
		getArtistsListUseCase.getArtists("Test", 15, "")
		coVerify(exactly = 1) {
			artistRepository.getArtists(any(), any(), any())
		}
	}

	@Test
	fun `given parameters get called the repository return expected data`() = testCoroutineDispatcher.runBlockingTest {
		coEvery { artistRepository.getArtists(any(), any(), any()) } returns expectedData
		Assertions.assertThat(getArtistsListUseCase.getArtists("Test", 15, null)).usingRecursiveComparison()
			.isEqualTo(
				expectedData
			)
	}

	@After
	fun cleanUp() {
		testCoroutineDispatcher.cleanupTestCoroutines()
		Dispatchers.resetMain()
	}
}