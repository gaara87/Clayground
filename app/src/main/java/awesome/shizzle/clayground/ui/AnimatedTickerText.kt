package awesome.shizzle.clayground.ui

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun TickerTextDemo() {
    Column(Modifier.fillMaxSize()) {
        var changingNumber by remember { mutableStateOf(Random.nextInt(0, 1000)) }
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .heightIn(min = maxHeight)
                    .verticalScroll(rememberScrollState())
            ) {
                val animatedValue = animateIntAsState(changingNumber)
                Text(
                    text = animatedValue.value.toString(),
                    style = MaterialTheme.typography.h3,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "Always bottom aligned text",
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = "These will be scrollable in a tiny tiny screen",
                    style = MaterialTheme.typography.h1,
                )
            }
        }
        Button(onClick = { changingNumber = Random.nextInt(0, 1000) }) {}
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun TickerText(text: String) {
    val textMeasurer = rememberTextMeasurer(0)
    val textStyle = MaterialTheme.typography.h1
    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(text = text),
        style = textStyle,
        maxLines = 1,
        softWrap = false,
        overflow = TextOverflow.Clip,
    )
    val height = with(LocalDensity.current) { textLayoutResult.size.height.toDp() }
    val width = with(LocalDensity.current) { textLayoutResult.size.width.toDp() }
    Canvas(
        modifier = Modifier.size(height = height, width = width)
    ) {
        drawText(
            textMeasurer = textMeasurer,
            text = text,
            style = textStyle,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Clip,
        )
    }
}

@Preview
@Composable
fun PreviewTickerText() {
    TickerText("Akash")
}