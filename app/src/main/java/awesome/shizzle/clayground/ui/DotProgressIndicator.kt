package awesome.shizzle.clayground.ui

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import awesome.shizzle.clayground.ui.DotProgressIndicatorState.Companion.MAX_DOTS

@Composable
internal fun DotProgressIndicatorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        val dotState = rememberDotProgressIndicatorState(
            activeDotCount = 0,
            totalDots = 6
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DotProgressIndicator(
                dotProgressIndicatorState = dotState,
            )
        }
        IndicatorConfigurations(
            addCount = { dotState.activeCount++ },
            removeCount = { dotState.activeCount-- }
        )
    }
}

@Composable
private fun IndicatorConfigurations(
    addCount: () -> Unit,
    removeCount: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = addCount,
        ) { Text(text = "+") }
        Button(
            modifier = Modifier.weight(1f),
            onClick = removeCount,
        ) { Text(text = "-") }
    }
}

@Composable
fun rememberDotProgressIndicatorState(
    @IntRange(from = 0, to = MAX_DOTS) activeDotCount: Int = 0,
    @IntRange(from = 1, to = MAX_DOTS) totalDots: Int = 0,
): DotProgressIndicatorState = rememberSaveable(saver = DotProgressIndicatorState.Saver) {
    DotProgressIndicatorState(
        activeDotCount = activeDotCount,
        totalPages = totalDots
    )
}

@Stable
class DotProgressIndicatorState(
    @IntRange(from = 0, to = MAX_DOTS) activeDotCount: Int = 0,
    @IntRange(from = 1, to = MAX_DOTS) val totalPages: Int = 0,
) {
    private var _activeCount by mutableStateOf(activeDotCount)

    @get:IntRange(from = 0, to = MAX_DOTS)
    var activeCount: Int
        get() = _activeCount
        internal set(value) {
            val coercedValue = value.coerceIn(0, totalPages)
            if (coercedValue != _activeCount) {
                _activeCount = coercedValue
            }
        }

    companion object {
        const val MAX_DOTS = 10L
        val Saver = listSaver<DotProgressIndicatorState, Any>(
            save = { listOf(it._activeCount, it.totalPages) },
            restore = { DotProgressIndicatorState(it[0] as Int, it[1] as Int) }
        )
    }
}

@Composable
private fun DotProgressIndicator(
    dotProgressIndicatorState: DotProgressIndicatorState,
    modifier: Modifier = Modifier,
    activeColor: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    inactiveColor: Color = activeColor.copy(ContentAlpha.disabled),
    indicatorShape: Shape = CircleShape,
) {

    val spacing = 8.dp

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val defaultIndicatorModifier = Modifier
                .size(16.dp)
                .background(color = inactiveColor, shape = indicatorShape)

            repeat(dotProgressIndicatorState.totalPages) { index ->
                val contextModifier = if (index <= dotProgressIndicatorState.activeCount - 1) {
                    defaultIndicatorModifier.then(
                        Modifier.background(
                            color = activeColor,
                            shape = indicatorShape
                        )
                    )
                } else {
                    defaultIndicatorModifier
                }
                Box(contextModifier)
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    DotProgressIndicatorScreen()
}
