package leegroup.module.core.util

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)

object System {
    fun currentTimeMillis() = Clock.System.now().toEpochMilliseconds()
}