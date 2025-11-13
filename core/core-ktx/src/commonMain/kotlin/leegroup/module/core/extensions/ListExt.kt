package leegroup.module.core.extensions

fun <T> List<T>.updateItemInList(
    updatedItem: T,
    areItemsEqual: (T, T) -> Boolean
): List<T> {
    return map { item ->
        if (areItemsEqual(item, updatedItem)) updatedItem else item
    }
}

fun <T> List<T>.insertToStart(item: T) = listOf(item) + this
