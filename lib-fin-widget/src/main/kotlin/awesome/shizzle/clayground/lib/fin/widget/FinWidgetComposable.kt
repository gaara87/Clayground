package awesome.shizzle.clayground.lib.fin.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.preferencesOf
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.ExperimentalGlanceRemoteViewsApi
import androidx.glance.layout.Column
import androidx.glance.text.Text
import com.google.android.glance.appwidget.host.glance.GlanceAppWidgetHostPreview


@Composable
internal fun FinWidgetComposable(modifier: GlanceModifier) {
    Column(modifier = modifier) {
        Text("I'm a cool fin widgy")
    }
}

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