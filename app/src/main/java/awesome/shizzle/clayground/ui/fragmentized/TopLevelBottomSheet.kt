package awesome.shizzle.clayground.ui.fragmentized

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopLevelBottomSheet(
    sheetState: ModalBottomSheetState,
    modifier: Modifier = Modifier,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit,
) {
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetContent = sheetContent,
        sheetState = sheetState,
        content = content,
    )
}

@OptIn(ExperimentalMaterialApi::class)
class TopLevelBottomSheetState(
    val modalBottomSheetState: ModalBottomSheetState,
) {
    fun showSheet(sheetContent: @Composable ColumnScope.() -> Unit) {

    }

}

internal val LocalTopLevelBottomSheet = staticCompositionLocalOf<TopLevelBottomSheetState> {
    error("Initialize me")
}
