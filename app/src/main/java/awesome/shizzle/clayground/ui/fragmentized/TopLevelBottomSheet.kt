package awesome.shizzle.clayground.ui.fragmentized

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopLevelBottomSheet(
    sheetState: ModalBottomSheetState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.DarkGray)
            )
        },
        sheetState = sheetState,
        content = content,
    )
}

@OptIn(ExperimentalMaterialApi::class)
internal val LocalTopLevelBottomSheet = staticCompositionLocalOf<ModalBottomSheetState> {
    error("Initialize me")
}
