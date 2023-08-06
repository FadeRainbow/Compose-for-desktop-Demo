package screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.res.ResourceLoader
import androidx.compose.ui.unit.dp
import com.konyaco.fluent.FluentTheme
import com.konyaco.fluent.animation.FluentDuration
import com.konyaco.fluent.animation.FluentEasing
import com.konyaco.fluent.component.TextField
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import org.jetbrains.skia.Shader
import utils.ColorUtils
import utils.helloWorldHelper
import view.ShowNeko
import viewmodel.NekoViewModel
import viewmodel.ViewModel
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

/**
 *@author FadeRainbow
 *@date 2023/7/27
 *@time 20:19
 */
@Composable
fun HomeScreen(viewModel: ViewModel,nekoViewModel:NekoViewModel) {
    Column(
        modifier = Modifier.padding(50.dp)
    ) {
        Text(text = "HelloWorld", style = FluentTheme.typography.title)
        DisplayCurrentTime(viewModel.timeUpdateDelay.toLong(),viewModel)
        TextField(
            modifier = Modifier.padding(top =10.dp).width(300.dp),
            value =viewModel.text,
            onValueChange = {viewModel.text = it},
            header = {
                Text("我是输入框")
            },
            singleLine = true
        )
        //Sexy
        if (viewModel.text.text=== helloWorldHelper()){
            exitProcess(1145141919810.toInt())
        }
    }
}
@Composable
private fun DisplayCurrentTime(delay:Long,viewModel:ViewModel) {
    var currentDateTime by remember { mutableStateOf(LocalDateTime.now()) }
    LaunchedEffect(Unit) {
        while (true) {
            currentDateTime = LocalDateTime.now()
            delay(delay) //更新时间延迟
            yield()
        }
    }
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val nanosFormatter = DateTimeFormatter.ofPattern("S")
    when(viewModel.exactTime){
        false -> Text(text = "宝贝，当前时间是${currentDateTime.format(dateFormatter)} ${currentDateTime.format(timeFormatter)}:${currentDateTime.format(nanosFormatter)}", style = FluentTheme.typography.bodyStrong)
        true -> Text(text ="宝贝,当前时间是${currentDateTime}", style = FluentTheme.typography.bodyStrong)
    }
}
