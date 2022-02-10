package common.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val CellAlpha = 0.12f


@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        modifier =
        Modifier
            .border(1.dp, MaterialTheme.colors.onSurface.copy(alpha = CellAlpha))
            .weight(weight)
            .padding(8.dp).fillMaxHeight()
    )
}