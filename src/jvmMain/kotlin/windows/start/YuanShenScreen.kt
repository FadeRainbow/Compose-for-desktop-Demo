package windows.start

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.konyaco.fluent.animation.FluentDuration
import com.konyaco.fluent.animation.FluentEasing
import com.konyaco.fluent.component.Dialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import windows.WindowViewModel
import kotlin.math.exp
import kotlin.math.expm1

/**
 *@author FadeRainbow
 *@date 2023/8/31
 *@time 9:13
 */
@Composable
fun YuanShenWindow(viewModel:WindowViewModel){
    Window(
        onCloseRequest = { },
        state = rememberWindowState(
            position = WindowPosition(Alignment.Center),
            width = 1366.dp, height = 768.dp
        ),
        visible = viewModel.showYuanShenWindow,
        title = ""
    ){
        var visible by remember { mutableStateOf(false) }
        var fadeType by remember { mutableStateOf(FadeType.FADE_OUT) }
        LaunchedEffect(Unit){
            delay(2500)
            fadeType=FadeType.FADE_IN
            visible=true
        }
            val alpha by animateFloatAsState(
                if (fadeType==FadeType.FADE_IN)1f else 0f,
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
