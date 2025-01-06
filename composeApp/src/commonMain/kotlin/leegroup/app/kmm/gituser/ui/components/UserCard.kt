package leegroup.app.kmm.gituser.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.background
    ) {
        content()
    }
}

@Preview
@Composable
private fun UserCardPreview() {
    MaterialTheme {
        UserCard(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(80.dp),
        ) {
            // UserCard content
        }
    }
}
