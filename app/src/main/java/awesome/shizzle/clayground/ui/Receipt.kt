package awesome.shizzle.clayground.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import awesome.shizzle.clayground.ui.theme.ClaygroundTheme
import awesome.shizzle.clayground.ui.theme.Shapes
import awesome.shizzle.clayground.ui.theme.Surface200

data class ReceiptData(
    val title: String,
    val subtitle: String,
    val rows: List<Row>,
) {
    data class Row(
        val label: String,
        val value: String,
    )
}

class SampleReceiptDataProvider : PreviewParameterProvider<ReceiptData> {
    override val values = sequenceOf(
        ReceiptData(
            title = "Send 1000 Sats",
            subtitle = "Sats have been sent wohoo!",
            rows = listOf(
                ReceiptData.Row(label = "Send to", value = "12345678650jashdbak"),
                ReceiptData.Row(label = "Network fee", value = "20 Sats"),
            )
        ),
        ReceiptData(
            title = "Send 2000 Sats",
            subtitle = "Sats have been sent wohoo!",
            rows = listOf(
                ReceiptData.Row(label = "Send to", value = "poiuytrewqlkjhgfdsamnbvcxz"),
                ReceiptData.Row(label = "Network fee", value = "40 Sats"),
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(@PreviewParameter(SampleReceiptDataProvider::class) receiptData: ReceiptData) {
    ClaygroundTheme {
        Surface(
            color = Surface200
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                Receipt(receiptData)
            }
        }
    }
}

@Composable
private fun Receipt(receiptData: ReceiptData) {
    Card(
        elevation = 4.dp,
        shape = Shapes.medium,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = receiptData.title, style = MaterialTheme.typography.h4)
            Text(
                text = receiptData.subtitle,
                style = MaterialTheme.typography.subtitle1,
            )
            Spacer(modifier = Modifier.requiredHeight(16.dp))
            for (row in receiptData.rows) {
                ValueRow(row)
            }
            Spacer(modifier = Modifier.requiredHeight(16.dp))
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Done", style = MaterialTheme.typography.button)
            }
            Spacer(modifier = Modifier.requiredHeight(8.dp))
            OutlinedButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "View transfer", style = MaterialTheme.typography.button)
            }
            Spacer(modifier = Modifier.requiredHeight(16.dp))
        }
    }
}

@Composable
private fun ValueRow(receiptDataRow: ReceiptData.Row) {
    Column(
        modifier = Modifier.padding(
            vertical = 8.dp
        )
    ) {
        Text(text = receiptDataRow.label, style = MaterialTheme.typography.caption)
        Text(text = receiptDataRow.value, style = MaterialTheme.typography.body1)
    }

}
