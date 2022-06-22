package awesome.shizzle.clayground.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import awesome.shizzle.clayground.ui.fragmentized.LocalTopLevelBottomSheet
import awesome.shizzle.clayground.ui.fragmentized.TopLevelBottomSheetState

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Surface200,
    /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ClaygroundTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val topLevelBottomSheetState = TopLevelBottomSheetState(
        modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    )
    CompositionLocalProvider(
        LocalTopLevelBottomSheet provides topLevelBottomSheetState
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}
