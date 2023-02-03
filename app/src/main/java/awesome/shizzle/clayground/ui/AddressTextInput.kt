package awesome.shizzle.clayground.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddressTextInput() {
    var shake by rememberSaveable { mutableStateOf(false) }
    val xOffset by animateFloatAsState(
        targetValue = if (shake) 0.1f else 0f,
        animationSpec = keyframes {
            durationMillis = 100
            0f at 0
            -10f at 33
            10f at 66
        },
        finishedListener = {
            shake = false
        }
    )
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    var value by remember { mutableStateOf(TextFieldValue()) }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
            .offset(xOffset.dp, 0.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                if (it.text.length > 3) {
                    shake = true
                }
                value = validateAndFormatInput(it)
            },
            singleLine = false,
            maxLines = 3,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.clearFocus() }
            ),
            textStyle = MaterialTheme.typography.h1,
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}

private fun validateAndFormatInput(input: TextFieldValue): TextFieldValue {
    val filteredText = regexToValidate.find(input.text)?.value ?: ""
    return input.copy(annotatedString = styleAddress(filteredText))
}

private fun styleAddress(address: String) = if (address.length >= 20) {
    buildAnnotatedString {
        withStyle(SpanStyle(color = Color.Black)) {
            append(address.substring(0, 5))
        }
        withStyle(SpanStyle(color = Color.Gray)) {
            append(address.substring(5, address.length - 5))
        }
        withStyle(SpanStyle(color = Color.Black)) {
            append(address.substring(address.length - 5, address.length))
        }
    }
} else {
    AnnotatedString(address)
}

private val regexToValidate = Regex("[a-zA-Z0-9]{0,40}")
