package com.vtech.mobile.compose_weather.city_edit_view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.vtech.mobile.compose_weather.R
import com.vtech.mobile.compose_weather.graph.WeatherGraph
import com.vtech.mobile.compose_weather.main_view_background_view.MainViewBackgroundView
import com.vtech.mobile.compose_weather.neumorphism_card_square_view.NeumorphismCardSquareView
import com.vtech.mobile.compose_weather.ui.theme.MainTheme
import com.vtech.mobile.compose_weather.ui.theme.getMainViewTopViewCardColor
import com.vtech.mobile.compose_weather.ui.theme.getTextHintColor
import com.vtech.mobile.compose_weather.ui.theme.getTextPrimaryColor
import soup.neumorphism.NeumorphImageButton
import soup.neumorphism.ShapeType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CityEditView(
    modifier: Modifier = Modifier,
    preview: Boolean = false
) {
    val height = 90.dp
    val infoDialogOpen = remember {
        mutableStateOf(false)
    }
    val userAction = Mvp(preview = preview).createUserAction()
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(Modifier.padding(start = 12.dp, end = 12.dp)) {
        val light = MaterialTheme.colors.isLight
        val topStartShadowColor = if (light) "#C6CEDA" else "#2C3341"
        val bottomEndShadowColor = if (light) "#FEFEFF" else "#404B5F"
        AndroidView(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            factory = { context ->
                NeumorphImageButton(context).apply {
                    setShadowColorLight(android.graphics.Color.parseColor(bottomEndShadowColor))
                    setShadowColorDark(android.graphics.Color.parseColor(topStartShadowColor))
                    setShapeType(ShapeType.PRESSED)
                }
            },
            update = { view ->
                view.invalidate()
            })
        var text by remember {
            mutableStateOf(TextFieldValue(""))
        }
        var hintVisible by remember { mutableStateOf(true) }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(height)
        ) {
            TextField(value = text, onValueChange = {
                text = it
                hintVisible = it.text.isEmpty()
            },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.city_edit_view_loop),
                        contentDescription = stringResource(
                            id = R.string.city_edit_view_icon_content_description
                        )
                    )
                },
                trailingIcon = {
                    Image(painter = painterResource(id = R.drawable.city_edit_view_ic_outline_info_24),
                        contentDescription = "info about this app",
                        modifier = Modifier.clickable {
                            userAction.onInfoClicked()
                            infoDialogOpen.value = true
                        }
                    )
                },
                textStyle = TextStyle(
                    color = MaterialTheme.colors.getTextPrimaryColor(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(900)
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hideSoftwareKeyboard()
                        userAction.onCityValidated(text.text)
                        text = TextFieldValue("")
                        hintVisible = true
                    }
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.getTextPrimaryColor(),
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(start = 24.dp, end = 24.dp)
            )
            if (hintVisible) {
                Text(
                    text = stringResource(id = R.string.city_edit_view_hint),
                    color = MaterialTheme.colors.getTextHintColor(),
                    fontWeight = FontWeight(900),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 81.dp, end = 74.dp)
                )
            }
        }
    }
    if (infoDialogOpen.value) {
        AlertDialog(onDismissRequest = {
            infoDialogOpen.value = false
        },
            title = {
                Text(
                    text = stringResource(id = R.string.city_edit_view_dialog_title),
                    fontSize = 22.sp,
                    fontWeight = FontWeight(900)
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.city_edit_view_dialog_subtitle),
                    fontSize = 12.sp,
                    fontWeight = FontWeight(700)
                )
            },
            backgroundColor = MaterialTheme.colors.getMainViewTopViewCardColor(),
            confirmButton = {
                Column {
                    NeumorphismCardSquareView(modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                        shapeType = ShapeType.DEFAULT,
                        onclick = {
                            openUrl("https://openweathermap.org/")
                            infoDialogOpen.value = false
                        }
                    ) {
                        Text(
                            text = "openweathermap.org",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(900),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        )
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun CityEditViewLightPreview() {
    MainTheme {
        Surface {
            MainViewBackgroundView {
                CityEditView(preview = true)
            }
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun CityEditViewDarkPreview() {
    MainTheme(darkTheme = true) {
        Surface {
            MainViewBackgroundView {
                CityEditView(preview = true)
            }
        }
    }
}

private fun openUrl(url: String) {
    val context = WeatherGraph.getContext()
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    if (context !is Activity) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(intent)
}

private class Mvp(
    private val preview: Boolean
) {
    private fun createScreen() = object : CityEditViewContract.Screen {
    }

    fun createUserAction(): CityEditViewContract.UserAction {
        if (preview) {
            return object : CityEditViewContract.UserAction {
                override fun onCityValidated(text: String) {}

                override fun onInfoClicked() {}
            }
        }
        return CityEditViewPresenter(
            createScreen(),
            WeatherGraph.getCityManager()
        )
    }
}