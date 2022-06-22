package awesome.shizzle.clayground.ui.fragmentized

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"

@OptIn(ExperimentalMaterialApi::class)
class NumberedFragment : Fragment() {
    private var param1: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            val scope = rememberCoroutineScope()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    val topLevelBottomSheetState = LocalTopLevelBottomSheet.current
                    var count by remember { mutableStateOf(0) }
                    Text(text = param1.toString())
                    Button(
                        onClick = {
                            count++
                            scope.launch {
                                topLevelBottomSheetState.showSheet {
                                    Text(
                                        text = "Count $count",
                                        color = Color.Red
                                    )
                                }
                            }
                        }
                    ) {
                        Text(text = "click moi")
                    }
                }

            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            NumberedFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}
