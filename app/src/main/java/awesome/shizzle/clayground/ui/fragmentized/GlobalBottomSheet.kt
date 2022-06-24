package awesome.shizzle.clayground.ui.fragmentized

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GlobalBottomSheet(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val bottomSheetContent = LocalGlobalBottomSheet.current
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetBackgroundColor = Color.Gray,
        sheetContent = {
            Box(
                modifier = Modifier
                    .padding(16.dp),
            ) {
                bottomSheetContent.sheetContent.value(this@ModalBottomSheetLayout)
            }
        },
        sheetState = bottomSheetContent.modalBottomSheetState,
        content = content,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Stable
class GlobalBottomSheetState(
    val modalBottomSheetState: ModalBottomSheetState
) {

    val sheetContent: MutableState<@Composable ColumnScope.() -> Unit> = mutableStateOf({})

    fun setBottomSheetContent(content: @Composable ColumnScope.() -> Unit) {
        sheetContent.value = content
    }

    suspend fun show() {
        modalBottomSheetState.show()
    }

    suspend fun showSheet(content: @Composable ColumnScope.() -> Unit) {
        setBottomSheetContent(content)
        show()
    }
}

internal val LocalGlobalBottomSheet = staticCompositionLocalOf<GlobalBottomSheetState> {
    error("Initialize me")
}
