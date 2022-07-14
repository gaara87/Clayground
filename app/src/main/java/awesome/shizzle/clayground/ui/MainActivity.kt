package awesome.shizzle.clayground.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import awesome.shizzle.clayground.ui.theme.ClaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClaygroundTheme {
                DotProgressIndicatorScreen()
            }
        }
    }
}
