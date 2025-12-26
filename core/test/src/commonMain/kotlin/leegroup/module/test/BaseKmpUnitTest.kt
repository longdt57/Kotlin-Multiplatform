package leegroup.module.test

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import leegroup.module.core.util.DispatchersProvider

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseKmpUnitTest {

    protected val coroutinesRule = KmpCoroutineTestRule()

    protected val testDispatcher get() = coroutinesRule.testDispatcher
    protected val testScope: TestScope = TestScope(testDispatcher)
    protected val testDispatchersProvider = object : DispatchersProvider {
        override val io: CoroutineDispatcher get() = testDispatcher
        override val main: CoroutineDispatcher get() = testDispatcher
        override val default: CoroutineDispatcher get() = testDispatcher
    }

    open fun setUp() {
        coroutinesRule.starting()
    }

    open fun tearDown() {
        coroutinesRule.finished()
    }
}