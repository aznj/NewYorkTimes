package dev.aznj.newyorktimes.interactors.mostpopular

import com.google.gson.GsonBuilder
import dev.aznj.newyorktimes.cache.AppDatabaseFake
import dev.aznj.newyorktimes.cache.MostEmailedDaoFake
import dev.aznj.newyorktimes.cache.MostSharedDaoFake
import dev.aznj.newyorktimes.cache.MostViewedDaoFake
import dev.aznj.newyorktimes.cache.model.MostEmailedEntityMapper
import dev.aznj.newyorktimes.cache.model.MostSharedEntityMapper
import dev.aznj.newyorktimes.cache.model.MostViewedEntityMapper
import dev.aznj.newyorktimes.domain.model.MostPopular
import dev.aznj.newyorktimes.network.ApiService
import dev.aznj.newyorktimes.network.data.MockWebServerResponses.mostPopularResponse
import dev.aznj.newyorktimes.network.model.MostPopularDtoMapper
import dev.aznj.newyorktimes.presentation.ui.MostPopularRepository
import dev.aznj.newyorktimes.presentation.ui.MostPopularRepository.Companion.MOST_VIEWED
import java.net.HttpURLConnection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MostPopularTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl
    private val appDatabase = AppDatabaseFake()
    private val DUMMY_TOKEN = "qwewqeqweqwe"

    // system in test
    private lateinit var mostPopularRepository: MostPopularRepository

    // dependencies
    private lateinit var apiService: ApiService
    private lateinit var mostViewedDao: MostViewedDaoFake
    private lateinit var mostSharedDao: MostSharedDaoFake
    private lateinit var mostEmailedDao: MostEmailedDaoFake
    private val dtoMapper = MostPopularDtoMapper()
    private val mostViewedEntityMapper = MostViewedEntityMapper()
    private val mostSharedEntityMapper = MostSharedEntityMapper()
    private val mostEmailedEntityMapper = MostEmailedEntityMapper()

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/svc/mostpopular/v2/")
        apiService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)

        mostViewedDao = MostViewedDaoFake(appDatabase)
        mostEmailedDao = MostEmailedDaoFake(appDatabase)
        mostSharedDao = MostSharedDaoFake(appDatabase)

        // instantiate system in test
        mostPopularRepository = MostPopularRepository(
            mostViewedDao = mostViewedDao,
            mostEmailedDao = mostEmailedDao,
            mostSharedDao = mostSharedDao,
            mostViewedEntityMapper = mostViewedEntityMapper,
            mostEmailedEntityMapper = mostEmailedEntityMapper,
            mostSharedEntityMapper = mostSharedEntityMapper,
            apiService = apiService,
            mostPopularDtoMapper = dtoMapper
        )
    }

    /*
    * 1. Are the most popular list retrieved from the network
    * 2. Are the most popular list inserted into the cache?
    * 3. Are the most popular list then emitted as a FLOW from the cache to the UI
    * */
    @Test
    fun getMostViewedFromNetwork_emitMostViewedFromCache(): Unit =
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(mostPopularResponse)
            )

            // confirm the cache is empty to start
            assert(mostViewedDao.getAllMostVieweds().isEmpty())

            val flowItems = mostPopularRepository.getMostViewed(
                token = DUMMY_TOKEN,
                listType = MOST_VIEWED
            ).toList()

            // confirm the cache is no longer empty
            assert(mostViewedDao.getAllMostVieweds().isNotEmpty())

            // first emission should be LOADING
            assert(flowItems[0].loading)

            // second emission should be the list of most viewed
            val mostViewed = flowItems[1].data
            assert(mostViewed?.size ?: 0 > 0)

            // confirm they are actually most viewed objects
            assert(mostViewed?.get(index = 1) is MostPopular)

            // ensure loading is false now because the use case is executed
            assert(!flowItems[1].loading)
        }

    @Test
    fun getMostViewedFromNetwork_emitNoData() : Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("{}")
        )

        val flowItems = mostPopularRepository.getMostViewed(
            DUMMY_TOKEN, MOST_VIEWED).toList()

        assert(flowItems[0].loading)

        val data = flowItems[1].data
        data?.let {
            assert(it.isEmpty())
        }

        assert(!flowItems[1].loading)
    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }
}