package awesome.shizzle.clayground.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import awesome.shizzle.clayground.ui.theme.Purple500
import awesome.shizzle.clayground.ui.theme.Surface200
import awesome.shizzle.clayground.ui.theme.Surface50

@Composable
fun SegmentedControl(
    modifier: Modifier = Modifier,
    activeIndex: Int,
    options: List<SegmentedControlOption>,
    onIndexSelected: (Int) -> Unit,
    isFullWidth: Boolean = true,
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Surface200,
                shape = RoundedCornerShape(percent = 50),
            )
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        options.forEachIndexed { index, option ->
            val isActive = index == activeIndex
            val placement = when (index) {
                0 -> SegmentedControlPlacement.START
                options.size - 1 -> SegmentedControlPlacement.END
                else -> SegmentedControlPlacement.CENTER
            }

            // Show a divider line between the start and center buttons if appropriate
            if (placement == SegmentedControlPlacement.CENTER) VerticalDivider()

            val boxModifier = if(isFullWidth) {
                Modifier.weight(1f)
            } else {
                Modifier
            }
            Box(
                modifier = boxModifier
                    .clip(placement.getRoundedCornerShape())
                    .background(
                        if (isActive) Surface200
                        else Surface50
                    )
                    .clickable(
                        role = Role.Tab,
                    ) {
                        onIndexSelected(index)
                    }
            ) {
                SegmentedControlContent(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(
                            start = placement.startPaddingDp.dp,
                            end = placement.endPaddingDp.dp,
                        ),
                    option = option,
                    isActive = isActive,
                )
            }

            // Show a divider line between the center and end buttons if appropriate
            if (placement == SegmentedControlPlacement.CENTER) VerticalDivider()
        }
    }
}

@Composable
private fun SegmentedControlContent(
    modifier: Modifier = Modifier,
    option: SegmentedControlOption,
    isActive: Boolean,
) {
    val foregroundColor = if (isActive) Surface50
    else Surface200
    Row(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(8.dp)
    ) {
        if (option.icon != null) {
            Icon(
                imageVector = option.icon,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = foregroundColor,
            )
        }
        if (option.title.isNotBlank()) {
            Text(
                text = option.title,
                fontWeight = if (isActive) FontWeight.Bold else null,
                color = foregroundColor,
            )
        }
    }
}

@Composable
private fun VerticalDivider() {
    Divider(
        modifier = Modifier
            .fillMaxHeight()
            .width(1.dp),
        color = Purple500,
    )
}

data class SegmentedControlOption(
    val title: String,
    val icon: ImageVector? = null,
)

/**
 * Spacing and border radius information dependent on the tab in the segmented control.
 */
private enum class SegmentedControlPlacement(
    val startRadiusPercent: Int,
    val endRadiusPercent: Int,
    val startPaddingDp: Int,
    val endPaddingDp: Int,
) {

    START(
        startRadiusPercent = 50,
        endRadiusPercent = 0,
        startPaddingDp = 12,
        endPaddingDp = 8,
    ),
    END(
        startRadiusPercent = 0,
        endRadiusPercent = 50,
        startPaddingDp = 8,
        endPaddingDp = 12,
    ),
    CENTER(
        startRadiusPercent = 0,
        endRadiusPercent = 0,
        startPaddingDp = 8,
        endPaddingDp = 8,
    ),
    ;

    @Composable
    fun getRoundedCornerShape(): RoundedCornerShape {
        return RoundedCornerShape(
            topStartPercent = startRadiusPercent,
            bottomStartPercent = startRadiusPercent,
            topEndPercent = endRadiusPercent,
            bottomEndPercent = endRadiusPercent,
        )
    }
}

@Composable
@Preview
fun Preview_SegmentedControl() {
    Column(
        Modifier.width(500.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SegmentedControl(
            activeIndex = 0,
            options = listOf(
                SegmentedControlOption(
                    title = "call",
                    icon = Icons.Filled.Call,
                ),
                SegmentedControlOption(
                    title = "me",
                    icon = Icons.Filled.Star,
                ),
                SegmentedControlOption(
                    title = "not",
                    icon = Icons.Filled.Star,
                )
            ),
            onIndexSelected = {}
        )
        SegmentedControl(
            activeIndex = 1,
            options = listOf(
                SegmentedControlOption(
                    title = "more text",
                    icon = Icons.Filled.Call,
                ),
                SegmentedControlOption(
                    title = "",
                    icon = Icons.Filled.Star,
                )
            ),
            onIndexSelected = {},
            isFullWidth = false,
        )
        SegmentedControl(
            activeIndex = 1,
            options = listOf(
                SegmentedControlOption(
                    title = "",
                    icon = Icons.Filled.Call,
                ),
                SegmentedControlOption(
                    title = "second",
                    icon = Icons.Filled.Star,
                )
            ),
            onIndexSelected = {},
            isFullWidth = false,
        )
    }

}
