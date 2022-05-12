package awesome.shizzle.clayground.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import awesome.shizzle.clayground.ui.theme.Purple900

class ReceiveData(
    val coin: String,
    val address: String,
)

class SampleReceiveDataProvider : PreviewParameterProvider<ReceiveData> {
    override val values: Sequence<ReceiveData>
        get() = sequenceOf(
            ReceiveData(
                coin = "Bitcoin",
                address = "A1b2C3d4E5f6g7H8i9J0k1L2m3N4o5P6Q7R8"
            )
        )

}

@Composable
fun Receive(data: ReceiveData) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Wallet")
        Text(text = "Receive ${data.coin}")
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .defaultMinSize(minWidth = 200.dp, minHeight = 200.dp)
                .size(height = 50.dp, width = 50.dp)
                .background(color = Purple900)
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .background(color = Color.Gray, shape = CircleShape)
                .padding(8.dp, 8.dp)
        ) {
            Text(text = data.address)
        }
    }
}

@Preview
@Composable
fun DefaultPreview(@PreviewParameter(SampleReceiveDataProvider::class) receiveData: ReceiveData) {
    Receive(receiveData)
}


