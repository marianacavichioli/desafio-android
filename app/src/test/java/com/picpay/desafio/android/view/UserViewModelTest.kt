package com.picpay.desafio.android.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.utils.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userLiveDataObserver: Observer<Resource<List<User>>>

    private lateinit var viewModel: UserViewModel
    private val userRepository = mock<UserRepository>()
    private val testDispatcher = TestCoroutineDispatcher()
    private val users = listOf(
        User("url", "user 1", 1, "user1"),
        User("url", "user 2", 2, "user2"),
        User("url", "user 3", 3, "user3")
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()

        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun onSuccessTest() {
        whenever(userRepository.getUsers()).thenReturn(emitResource(Resource.Success(users)))

        viewModel = UserViewModel(userRepository)
        viewModel.users.observeForever(userLiveDataObserver)

        assertEquals(viewModel.users.value?.data, users)
        assertTrue(viewModel.users.value is Resource.Success<*>)
    }

    @Test
    fun onErrorTest() {
        whenever(userRepository.getUsers()).thenReturn(
            emitResource(
                Resource.Error(
                    Throwable(),
                    users
                )
            )
        )

        viewModel = UserViewModel(userRepository)
        viewModel.users.observeForever(userLiveDataObserver)

        assertTrue(viewModel.users.value is Resource.Error<*>)
    }

    @Test
    fun onLoadingTest() {
        whenever(userRepository.getUsers()).thenReturn(emitResource(Resource.Loading(users)))

        viewModel = UserViewModel(userRepository)
        viewModel.users.observeForever(userLiveDataObserver)

        assertTrue(viewModel.users.value is Resource.Loading<*>)
    }

    private fun emitResource(resource: Resource<List<User>>): Flow<Resource<List<User>>> {
        return flow {
            emit(resource)
        }
    }
}
