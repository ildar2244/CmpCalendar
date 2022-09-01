package ru.axdar.cmpcalendar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.axdar.ui_kit.components.buttons.CmpButtonAlternative
import ru.axdar.ui_kit.components.buttons.CmpButtonRed
import ru.axdar.ui_kit.components.buttons.CmpButtonTertiary
import ru.axdar.ui_kit.components.calendar.CalendarDialogBox
import ru.axdar.ui_kit.components.calendar.model.CalendarState
import ru.axdar.ui_kit.components.calendar.toDateLongMillis
import ru.axdar.ui_kit.core.buttons.ButtonType
import ru.axdar.ui_kit.core.theme.CmpAppTheme

private val buttonWidth = 150.dp

@OptIn(ExperimentalComposeUiApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CmpAppTheme {
                var calendarState by remember {
                    mutableStateOf(CalendarState.Hide)
                }
                var from by remember { mutableStateOf(-1L) }
                var to by remember { mutableStateOf(-1L) }
                val context = LocalContext.current

                CalendarDialogBox(
                    state = calendarState,
                    from = from,
                    to = to,
                    onConfirmClick = { f, t ->
                        calendarState = CalendarState.Hide
                        from = f.toDateLongMillis()
                        to = f.toDateLongMillis()
                        Toast.makeText(context, "Указали с $f по $t", Toast.LENGTH_SHORT).show()
                    },
                    onCancel = { calendarState = CalendarState.Hide },
                    onDismiss = { calendarState = CalendarState.Hide }
                ) {
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                            .background(Color.Gray.copy(.15f)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            CmpButtonAlternative(
                                modifier = Modifier.padding(top = 16.dp),
                                buttonType = ButtonType.LARGE,
                                buttonWidth = buttonWidth,
                                onClick = { calendarState = CalendarState.Show },
                                content = {
                                    Text(
                                        text = "Календарь",
                                        style = CmpAppTheme.typography.h3Regular
                                    )
                                }
                            )
                        }
                        item { Texts() }
                        item { Buttons() }
                    }
                }
            }
        }
    }
}

@Composable
fun Texts() {
    Column(
        Modifier.padding(start = 16.dp, top = 16.dp)
    ) {
        Text(
            text = "Headline 1 Black",
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.h1Black
        )
        Text(
            text = "Headline 1 Bold",
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.h1Bold
        )
        Text(
            text = "Headline 2 Black",
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            style = CmpAppTheme.typography.h2Black
        )
        Text(
            text = "Headline 2 Bold",
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.h2Bold
        )
        Text(
            text = "Headline 2 Medium",
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.h2Medium
        )
        Text(
            text = "Headline 3 Bold",
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            style = CmpAppTheme.typography.h3Bold
        )
        Text(
            text = "Headline 3 Medium",
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.h3Medium
        )
        Text(
            text = "Headline 3 Regular",
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.h3Regular
        )
    }

    Column(
        Modifier.padding(start = 16.dp, top = 16.dp)
    ) {
        Text(
            text = "Paragraph 1 Bold",
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.p1Bold
        )
        Text(
            text = "Paragraph 1 Medium",
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.p1Medium
        )
        Text(
            text = "Paragraph 1 Regular",
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.p1Regular
        )
        Text(
            text = "Paragraph 2 Bold",
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            style = CmpAppTheme.typography.p2Bold
        )
        Text(
            text = "Paragraph 2 Medium",
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.p2Medium
        )
        Text(
            text = "Paragraph 2 Regular",
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.p2Regular
        )
        Text(
            text = "Paragraph 2 Medium Uppercase".uppercase(),
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.p2MediumUpperCase
        )
        Text(
            text = "Paragraph 3 Bold",
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            style = CmpAppTheme.typography.p3Bold
        )
        Text(
            text = "Paragraph 3 Medium",
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.p3Medium
        )
        Text(
            text = "Paragraph 3 Regular",
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.p3Regular
        )
        Text(
            text = "Paragraph 3 Medium Uppercase".uppercase(),
            modifier = Modifier.padding(bottom = 8.dp),
            style = CmpAppTheme.typography.p3MediumUpperCase
        )
    }
}

@Composable
@ExperimentalComposeUiApi
fun Buttons() {
    var isLoading by remember {
        mutableStateOf(false)
    }
    val onClick: () -> Unit = {
        isLoading = !isLoading
    }

    Column {
        CmpButtonRed(
            modifier = Modifier.padding(16.dp),
            buttonType = ButtonType.SMALL,
            buttonWidth = buttonWidth,
            loading = isLoading,
            onClick = onClick,
            content = {
                Text(
                    text = "Кнопка",
                    style = CmpAppTheme.typography.p2Medium
                )
            }
        )
        CmpButtonRed(
            modifier = Modifier.padding(16.dp),
            buttonType = ButtonType.MEDIUM,
            buttonWidth = buttonWidth,
            loading = isLoading,
            onClick = onClick,
            content = {
                Text(
                    text = "Кнопка",
                    style = CmpAppTheme.typography.p2Medium
                )
            }
        )
        CmpButtonRed(
            modifier = Modifier.padding(16.dp),
            buttonType = ButtonType.LARGE,
            buttonWidth = buttonWidth,
            loading = isLoading,
            onClick = onClick,
            content = {
                Text(
                    text = "Кнопка",
                    style = CmpAppTheme.typography.p2Medium
                )
            }
        )
        CmpButtonTertiary(
            modifier = Modifier.padding(16.dp),
            buttonType = ButtonType.SMALL,
            buttonWidth = buttonWidth,
            loading = isLoading,
            onClick = onClick,
            content = {
                Text(
                    text = "Кнопка",
                    style = CmpAppTheme.typography.p2Medium
                )
            }
        )
        CmpButtonTertiary(
            modifier = Modifier.padding(16.dp),
            buttonType = ButtonType.MEDIUM,
            buttonWidth = buttonWidth,
            loading = isLoading,
            onClick = onClick,
            content = {
                Text(
                    text = "Кнопка",
                    style = CmpAppTheme.typography.p2Medium
                )
            }
        )
        CmpButtonTertiary(
            modifier = Modifier.padding(16.dp),
            buttonType = ButtonType.LARGE,
            buttonWidth = buttonWidth,
            loading = isLoading,
            onClick = onClick,
            content = {
                Text(
                    text = "Кнопка",
                    style = CmpAppTheme.typography.p2Medium
                )
            }
        )
    }

    Column {
        CmpButtonAlternative(
            modifier = Modifier.padding(16.dp),
            buttonType = ButtonType.SMALL,
            buttonWidth = buttonWidth,
            loading = isLoading,
            onClick = onClick,
            content = {
                Text(
                    text = "Кнопка",
                    style = CmpAppTheme.typography.p2Medium
                )
            }
        )
        CmpButtonAlternative(
            modifier = Modifier.padding(16.dp),
            buttonType = ButtonType.MEDIUM,
            buttonWidth = buttonWidth,
            loading = isLoading,
            onClick = onClick,
            content = {
                Text(
                    text = "Кнопка",
                    style = CmpAppTheme.typography.p2Medium
                )
            }
        )
        CmpButtonAlternative(
            modifier = Modifier.padding(16.dp),
            buttonType = ButtonType.LARGE,
            buttonWidth = buttonWidth,
            loading = isLoading,
            onClick = onClick,
            content = {
                Text(
                    text = "Кнопка",
                    style = CmpAppTheme.typography.p2Medium
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun DefaultPreview() {
    CmpAppTheme {

    }
}