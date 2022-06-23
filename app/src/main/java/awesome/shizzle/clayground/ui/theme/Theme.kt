package awesome.shizzle.clayground.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import awesome.shizzle.clayground.ui.fragmentized.LocalSheetContent
import awesome.shizzle.clayground.ui.fragmentized.LocalSheetContentUpdater

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

    var localSheetContent: (@Composable () -> Unit) by remember { mutableStateOf({}) }
    val updater: ((@Composable () -> Unit)) -> Unit = {
        localSheetContent = it
    }

    CompositionLocalProvider(
        LocalSheetContentUpdater provides updater,
        LocalSheetContent provides localSheetContent
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}
