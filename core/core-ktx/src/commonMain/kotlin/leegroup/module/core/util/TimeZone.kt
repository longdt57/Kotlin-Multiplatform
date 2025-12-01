package leegroup.module.core.util

import kotlinx.datetime.TimeZone

object TimeZone {
    fun getDefault() = TimeZone.currentSystemDefault()
}