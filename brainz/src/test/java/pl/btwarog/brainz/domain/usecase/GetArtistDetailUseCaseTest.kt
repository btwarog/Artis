package pl.btwarog.brainz.domain.usecase

import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions
import org.junit.*
import pl.btwarog.brainz.domain.error.ResultWrapper
import pl.btwarog.brainz.domain.repository.IArtistsRepository
import pl.btwarog.brainz.domain.util.ArtistBasicInfoDataFactory
import pl.btwarog.brainz.domain.util.ArtistDetailInfoDataFactory

class GetArtistDetailUseCaseTest {

	private val testCoroutineDispatcher: TestCoroutineDispatcher
		get() {
			return TestCoroutineDispatcher()
		}

	private val artistRepository: IArtistsRepository = mockk()

	private val getArtistsListUseCase = GetArtistDetailUseCase(artistRepository)

	private val expectedData = ResultWrapper.Success(ArtistDetailInfoDataFactory.getArtistDetailInfoDomain())

	init {
		coEvery { artistRepository.getArtistDetail(any()) } returns expectedData
	}

	@Before
	fun setup() {
		Dispatchers.setMain(testCoroutineDispatcher)
	}

	@Test
	fun `given parameters get called the repository gets called once`() = testCoroutineDispatcher.runBlockingTest {
		getArtistsListUseCase.getArtistDetail("Test")
		coVerify(exactly = 1) {
			artistRepository.getArtistDetail(any())
		}
	}

	@Test
	fun `given parameters get called the repository return expected data`() = testCoroutineDispatcher.runBlockingTest {
		Assertions.assertThat(getArtistsListUseCase.getArtistDetail("Test")).usingRecursiveComparison()
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