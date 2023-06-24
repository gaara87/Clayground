package awesome.shizzle.clayground.lib.fin.widget

import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.glance.appwidget.ExperimentalGlanceRemoteViewsApi
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.google.android.glance.tools.viewer.GlanceSnapshot
import com.google.android.glance.tools.viewer.GlanceViewerActivity

@OptIn(ExperimentalGlanceRemoteViewsApi::class)
class FinGlanceWidgetViewerActivity : GlanceViewerActivity() {

    override suspend fun getGlanceSnapshot(
        receiver: Class<out GlanceAppWidgetReceiver>
    ): GlanceSnapshot {
        return when (receiver) {
            FinGlanceAppWidgetReceiver::class.java -> GlanceSnapshot(
                instance = FinGlanceAppWidget(),
                state = mutablePreferencesOf(/*intPreferencesKey("state") to value*/),
            )

            else -> throw IllegalArgumentException()
        }
    }

    override fun getProviders() = listOf(FinGlanceAppWidgetReceiver::class.java)
}