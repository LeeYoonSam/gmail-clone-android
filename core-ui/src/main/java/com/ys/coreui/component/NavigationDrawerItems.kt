package com.ys.coreui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DrawerTitleItem(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
fun DrawerMenuItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.size(24.dp)
        )

        VerticalDivider(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
fun DrawerDivider() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = MaterialTheme.colorScheme.outline
    )
}

@Composable
fun DrawerCategory(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(8.dp),
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        style = MaterialTheme.typography.labelSmall
    )
}

@Composable
@Preview(showBackground = true)
private fun DrawerTitleItemPreview() {
    DrawerTitleItem("DrawerTitleItem")
}

@Composable
@Preview(showBackground = true)
private fun DrawerMenuItemPreview() {
    DrawerMenuItem(
        title = "DrawerMenuItem",
        icon = Icons.Filled.Inbox,
        onClick = {}
    )
}

@Composable
@Preview(showBackground = true)
private fun DrawerDividerPreview() {
    DrawerDivider()
}

@Composable
@Preview(showBackground = true)
private fun DrawerCategoryPreview() {
    DrawerCategory("DrawerCategory")
}