package leegroup.module.test

import org.junit.Rule

abstract class BaseUnitTest {

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    val testDispatcher get() = coroutinesRule.testDispatcher

}