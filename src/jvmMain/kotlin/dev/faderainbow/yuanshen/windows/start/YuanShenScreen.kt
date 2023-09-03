package dev.faderainbow.yuanshen.windows.start

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.konyaco.fluent.animation.FluentDuration
import com.konyaco.fluent.animation.FluentEasing
import dev.faderainbow.yuanshen.windows.WindowViewModel
import kotlinx.coroutines.delay

/**
 *@author FadeRainbow
 *@date 2023/8/31
 *@time 9:13
 */
@Composable
fun YuanShenWindow(viewModel: WindowViewModel){
    Window(
        onCloseRequest = {
        /*
        NotClose
        Because YuanShen is the best game in China and world so can not exit it :D
        */
                         },
        state = rememberWindowState(
            position = WindowPosition(Alignment.Center),
            width = 1366.dp, height = 768.dp
        ),
        visible = viewModel.showYuanShenWindow,
        title = "原神"
    ){
        var visible by remember { mutableStateOf(false) }
        var fadeType by remember { mutableStateOf(FadeType.FADE_OUT) }
        LaunchedEffect(Unit){
            delay(2510)
            fadeType= FadeType.FADE_IN
            visible=true
        }
            val alpha by animateFloatAsState(
                if (fadeType== FadeType.FADE_IN)1f else 0f,
                tween(FluentDuration.VeryLongDuration, easing = FluentEasing.FadeInFadeOutEasing)
            )
            if (visible) {
                Image(
                    painter = painterResource("start.png"),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    alpha=alpha
                )
            }
    }
}
private enum class FadeType{
    FADE_IN,FADE_OUT
}
