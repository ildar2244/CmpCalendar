package ru.axdar.ui_kit.components.calendar.units

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.axdar.ui_kit.R
import ru.axdar.ui_kit.core.theme.CmpAppTheme

@Composable
internal fun BottomPanel(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 12.dp)
            .sizeIn(minWidth = 344.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
internal fun BottomPanelWithSelectedDates(
    startDate: String,
    endDate: String
) {
    Row(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
            .size(width = 320.dp, height = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .size(width = 117.dp, height = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "${stringResource(id = R.string.start_date)}: ",
                style = CmpAppTheme.typography.p3MediumUpperCase,
                color = CmpAppTheme.colors.textSecondary
            )
            Text(
                text = startDate.ifEmpty { stringResource(R.string.date) },
                style = CmpAppTheme.typography.p3MediumUpperCase,
                color = CmpAppTheme.colors.textPrimary
            )
        }
        Row(
            modifier = Modifier
                .size(width = 117.dp, height = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "${stringResource(id = R.string.end_date)}: ",
                style = CmpAppTheme.typography.p3MediumUpperCase,
                color = CmpAppTheme.colors.textSecondary
            )
            Text(
                text = endDate.ifEmpty { stringResource(R.string.date) },
                textAlign = TextAlign.End,
                style = CmpAppTheme.typography.p3MediumUpperCase,
                color = CmpAppTheme.colors.textPrimary
            )
        }
    }
}

@Composable
internal fun BottomButtons(
    onCancelClick: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 8.dp)
            .size(width = 320.dp, height = 44.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        WhiteButton(
            modifier = Modifier
                .padding(end = 6.dp)
                .weight(0.5f),
            text = stringResource(R.string.button_label_cancel),
            onClick = onCancelClick
        )
        RedButton(
            modifier = Modifier
                .padding(end = 6.dp)
                .weight(0.5f),
            text = stringResource(R.string.button_label_done),
            onClick = onConfirmClick
        )
    }
}

@Composable
private fun RedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    SampleButton(
        modifier = modifier,
        text = text,
        textColor = CmpAppTheme.colors.textInverted,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = CmpAppTheme.colors.controlsPrimaryActive
        ),
        onClick = onClick
    )
}

@Composable
private fun WhiteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    SampleButton(
        modifier = modifier,
        text = text,
        textColor = CmpAppTheme.colors.textHeadline,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = CmpAppTheme.colors.controlsTertiaryActive
        ),
        onClick = onClick
    )
}

@Composable
private fun SampleButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Black,
    colors: ButtonColors,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .size(width = 144.dp, height = 44.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        colors = colors,
        contentPadding = PaddingValues(vertical = 10.dp),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp)
    ) {
        Text(
            text = text,
            style = CmpAppTheme.typography.p1Medium,
            color = textColor
        )
    }
}