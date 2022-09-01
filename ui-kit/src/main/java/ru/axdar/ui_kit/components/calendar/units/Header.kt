package ru.axdar.ui_kit.components.calendar.units

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import ru.axdar.ui_kit.core.theme.CmpAppTheme

@Composable
internal fun Header(
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 4.dp)
            .size(width = 344.dp, height = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        content()
    }
}

@Composable
internal fun YearSelectionPanel(
    month: String,
    year: String,
    onClick: () -> Unit,
    isRollOpen: Boolean = false
) {
    Row(
        modifier = Modifier
            .padding(start = 12.dp, top = 4.dp, bottom = 4.dp)
            .sizeIn(minWidth = 108.dp, minHeight = 24.dp)
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "$month $year",
            color = CmpAppTheme.colors.textHeadline,
            style = CmpAppTheme.typography.h3Medium
        )
        Image(
            modifier = Modifier
                .padding(start = 2.dp)
                .size(width = 16.dp, height = 16.dp),
            imageVector = if (isRollOpen) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            colorFilter = ColorFilter.tint(CmpAppTheme.colors.iconsPrimary),
            contentDescription = ""
        )
    }
}

@Composable
internal fun MonthSwitchButtons(
    onSlideMonthToBack: () -> Unit,
    onSlideMonthToForward: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(end = 4.dp)
            .size(width = 72.dp, height = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Surface(
            modifier = Modifier
                .padding(end = 4.dp)
                .size(32.dp),
            color = CmpAppTheme.colors.backgroundSecondary,
            shape = RoundedCornerShape(16.dp)
        ) {
            IconButton(onClick = onSlideMonthToBack) {
                Image(
                    imageVector = Icons.Default.ArrowBack,
                    colorFilter = ColorFilter.tint(CmpAppTheme.colors.iconsPrimary),
                    contentDescription = ""
                )
            }
        }
        Surface(
            modifier = Modifier
                .padding(start = 4.dp)
                .size(32.dp),
            color = CmpAppTheme.colors.backgroundSecondary,
            shape = RoundedCornerShape(16.dp)
        ) {
            IconButton(onClick = { onSlideMonthToForward() }) {
                Image(
                    imageVector = Icons.Default.ArrowForward,
                    colorFilter = ColorFilter.tint(CmpAppTheme.colors.iconsPrimary),
                    contentDescription = ""
                )
            }
        }
    }
}