package awesome.shizzle.clayground.ui

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.*

@Composable
fun CurrencyAmountTextInput() {
    var amountString by rememberSaveable { mutableStateOf("0.0") }
    BasicTextField(
        modifier = Modifier.onKeyEvent {
            true
        },
        value = amountString,
        onValueChange = { changedValue ->
            if (isNewInputValidCharacter(changedValue)) {
                amountString = changedValue
            }
        },
        visualTransformation = CurrencyVisualTransformation,
        textStyle = MaterialTheme.typography.displayMedium,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
    )
}

private fun isNewInputValidCharacter(input: String): Boolean {
    val re = Regex("[^0-9,.]")
    return re.find(input) == null
}

private object CurrencyVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedAnnotatedString = buildAnnotatedString {
            append("$")
            append(text)
            append("\nof Bitcoin")
        }

        return TransformedText(transformedAnnotatedString, object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset + 1
            }

            override fun transformedToOriginal(offset: Int): Int {
                return (offset - 1).coerceAtLeast(0)
            }
        })
    }

}
