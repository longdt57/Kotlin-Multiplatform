package leegroup.module.core.util

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
object UUID {
    fun randomUUID() = Uuid.random()

    val Uuid.mostSignificantBits
        get() = randomUUID().toLongs { mostSignificantBits, _ ->
            mostSignificantBits
        }
}