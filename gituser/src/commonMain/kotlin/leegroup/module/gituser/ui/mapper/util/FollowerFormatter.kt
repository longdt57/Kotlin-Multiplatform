package leegroup.module.gituser.ui.mapper.util

internal object FollowerFormatter {
    fun formatLargeNumber(value: Int, max: Int = 100): String {
        return if (value > max) "$max+" else value.toString()
    }
}