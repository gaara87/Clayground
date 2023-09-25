package awesome.shizzle.clayground.lib.fin.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.preferencesOf
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.ExperimentalGlanceRemoteViewsApi
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.text.Text
import awesome.shizzle.clayground.lib.fin.widget.blocks.HoldingItemRow
import awesome.shizzle.clayground.lib.fin.widget.blocks.HoldingItemRowState
import com.google.android.glance.appwidget.host.glance.GlanceAppWidgetHostPreview
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


@Composable
internal fun FinWidgetComposable(modifier: GlanceModifier) {
    val state by remember {
        mutableStateOf(
            FinWidgetState(
                rows = persistentListOf(
                    HoldingItemRowState(
                        symbol = "BTC",
                        quantity = "$30k",
                        value = "1",
                    ),
                    HoldingItemRowState(
                        symbol = "GOOGS",
                        quantity = "$1.3k",
                    ),
                )
            )
        )
    }
    LazyColumn(modifier = modifier.padding(16.dp)) {
        item {
            // would love to make this sticky
            Text(text = "Your holdings")
        }
        items(state.rows) {
            HoldingItemRow(modifier = GlanceModifier.fillMaxWidth(), state = it)
        }
    }
}

internal data class FinWidgetState(
    val rows: ImmutableList<HoldingItemRowState>
)

@OptIn(ExperimentalGlanceRemoteViewsApi::class)
@Preview
@Composable
private fun WidgetPreview() {
    GlanceAppWidgetHostPreview(
        modifier = Modifier.fillMaxSize(),
        glanceAppWidget = FinGlanceAppWidget(),
        state = preferencesOf(/*SampleGlanceWidget.countKey to 2*/),
        displaySize = DpSize(200.dp, 200.dp),
    )
}