package leegroup.module.core.extensions

fun String.formattedUrl(): String {
    return if (this.startsWith("http://") || this.startsWith("https://")) {
        this
    } else {
        "http://$this"
    }
}

@Suppress("MagicNumber")
fun String?.appVersionToInt(): Int {
    fun Int?.orZero() = this ?: 0

    return this?.split(".")?.mapIndexed { index, value ->
        when (index) {
            0 -> value.toIntOrNull().orZero() * 1000
            1 -> value.toIntOrNull().orZero() * 100
            else -> value.toIntOrNull().orZero()
        }
    }.orEmpty().sumOf { it }
}