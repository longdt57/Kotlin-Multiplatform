package leegroup.module.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class KmpCoroutineTestRule(
    val testDispatcher: TestDispatcher = StandardTestDispatcher(TestCoroutineScheduler()),
) {

    internal fun starting() {
        Dispatchers.setMain(testDispatcher)
    }

    internal fun finished() {
        Dispatchers.resetMain()
    }
}
