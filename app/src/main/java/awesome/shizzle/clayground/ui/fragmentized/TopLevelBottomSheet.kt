package awesome.shizzle.clayground.ui.fragmentized

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

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

internal val LocalSheetContentUpdater =
    staticCompositionLocalOf<((@Composable () -> Unit)) -> Unit> {
        error("Initialize me")
    }

internal val LocalSheetContent = staticCompositionLocalOf<@Composable () -> Unit> { { } }
