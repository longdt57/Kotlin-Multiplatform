package leegroup.app.kmm.gituser.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import leegroup.app.kmm.gituser.ui.theme.ComposeTheme

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun UserCardPreview() {
    ComposeTheme {
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
