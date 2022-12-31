package awesome.shizzle.clayground.ui

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlin.random.Random

@Composable
fun TickerText() {
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
