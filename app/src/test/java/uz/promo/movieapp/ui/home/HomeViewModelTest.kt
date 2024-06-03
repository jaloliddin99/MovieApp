package uz.promo.movieapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uz.promo.movieapp.data.remote.models.createTestResult
import uz.promo.movieapp.domain.state.Resource
import uz.promo.movieapp.domain.usecase.PersonDataUseCase

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var viewModel: HomeViewModel
    private val useCase = mockk<PersonDataUseCase>(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coEvery { useCase() } returns flowOf(Resource.Success(data = listOf(createTestResult(), createTestResult())))
        viewModel = HomeViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllPersons emits success updates state correctly`() = testScope.runTest {
        viewModel.getAllPersons()
        advanceUntilIdle()
        assertFalse(viewModel.state.value.isLoading)
        assertNotNull(viewModel.state.value.personList)
        assertTrue(viewModel.state.value.personList.isNotEmpty())
    }

    @Test
    fun `searchPersons updates filteredList based on query`() = testScope.runTest {
        viewModel.getAllPersons()
        advanceUntilIdle()
        viewModel.searchPersons("Test")
        assertTrue(viewModel.filteredList.value.any { it.name.contains("Test", ignoreCase = true) })
        viewModel.searchPersons("")
        assertTrue(viewModel.filteredList.value.size == viewModel.state.value.personList.size)
        viewModel.searchPersons("LLL")
        assertTrue(viewModel.filteredList.value.isEmpty())
    }

    @Test
    fun `showDialog toggles dialog state`() {
        assertFalse(viewModel.state.value.showDialog)
        viewModel.showDialog(true)
        assertTrue(viewModel.state.value.showDialog)
        viewModel.showDialog(false)
        assertFalse(viewModel.state.value.showDialog)
    }
}
