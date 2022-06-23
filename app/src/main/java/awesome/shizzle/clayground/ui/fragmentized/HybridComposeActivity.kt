@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)

package awesome.shizzle.clayground.ui.fragmentized

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import awesome.shizzle.clayground.ui.theme.ClaygroundTheme

lateinit var modalBottomSheetState: ModalBottomSheetState

class HybridComposeActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ClaygroundTheme {
                val sheetContent = LocalSheetContent.current
                modalBottomSheetState =
                    rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
                TopLevelBottomSheet(
                    sheetState = modalBottomSheetState,
                    sheetContent = {
                        Box(
                            modifier = Modifier
                                .background(Color.Gray)
                                .height(250.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            sheetContent()
                        }
                    },
                ) {
                    Scaffold(
                        modifier = Modifier.systemBarsPadding(),
                        topBar = { TopAppBar { Text(text = "this is the top bar") } },
                        bottomBar = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(54.dp)
                                    .background(Color.Cyan)
                            )
                        },
                    ) { paddingValues ->
                        FragmentContainer(
                            modifier = Modifier
                                .padding(paddingValues)
                                .fillMaxSize(),
                            fragmentManager = supportFragmentManager,
                            commit = { add(it, NumberedFragment.newInstance(1)) }
                        )
                    }
                }
            }
        }
    }
}
