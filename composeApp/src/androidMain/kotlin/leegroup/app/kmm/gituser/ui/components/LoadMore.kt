package leegroup.app.kmm.gituser.ui.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow

@Composable
fun LoadMore(listState: LazyListState, onLoadMore: () -> Unit) {
    // Detect when user scrolls near the bottom of the list
    var previousScrollOffset by remember { mutableIntStateOf(0) } // To track scroll direction
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { currentOffset ->
                val totalItemsCount = listState.layoutInfo.totalItemsCount
                val lastVisibleItemIndex =
                    listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                // Check if scrolling down and near the end of the list
                if (currentOffset > previousScrollOffset &&
                    lastVisibleItemIndex >= totalItemsCount - 2 &&
                    totalItemsCount > 0
                ) {
                    onLoadMore()
                }

                previousScrollOffset = currentOffset // Update the previous offset
            }
    }
}