package awesome.shizzle.clayground.lib.fin.widget.blocks

import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.text.Text

internal data class HoldingItemRowState(
    val symbol: String,
    val quantity: String,
    val value: String? = null,
)

@Composable
internal fun HoldingItemRow(
    modifier: GlanceModifier,
    state: HoldingItemRowState,
) {
    Row(modifier = modifier) {
        Column {
            Text(text = state.quantity)
            state.value?.let { Text(text = it) }
        }
        // chart here
        Spacer(GlanceModifier.defaultWeight())
        Text(
            text = state.symbol,
        )
    }
}