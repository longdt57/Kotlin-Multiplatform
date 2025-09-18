package leegroup.module.designsystem.components

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow

@Composable
fun LoadMoreGrid(
    fromBottom: Int = LOAD_MORE_FROM_BOTTOM_ITEM,
    gridState: LazyGridState,
    onLoadMore: () -> Unit
) {
    // Detect when user scrolls near the bottom of the grid
    var previousScrollOffset by remember { mutableIntStateOf(0) } // To track scroll direction
    LaunchedEffect(gridState) {
        snapshotFlow { gridState.firstVisibleItemScrollOffset }
            .collect { currentOffset ->
                val totalItemsCount =
                    gridState.layoutInfo.totalItemsCount.takeIf { it > 0 } ?: return@collect
                val lastVisibleItemIndex =
                    gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                // Check if scrolling down and near the end of the grid
                if (currentOffset > previousScrollOffset &&
                    lastVisibleItemIndex >= totalItemsCount - fromBottom
                ) {
                    onLoadMore()
                }

                previousScrollOffset = currentOffset // Update the previous offset
            }
    }
}
