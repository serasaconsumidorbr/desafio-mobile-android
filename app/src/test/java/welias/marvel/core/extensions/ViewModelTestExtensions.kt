@file:OptIn(ExperimentalCoroutinesApi::class)

package welias.marvel.core.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

fun runTestViewModel(testDispatcher: TestDispatcher, callback: () -> Unit) = runTest {
    Dispatchers.setMain(testDispatcher)

    callback()

    Dispatchers.resetMain()
}
