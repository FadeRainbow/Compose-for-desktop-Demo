package dev.faderainbow.yuanshen.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.konyaco.fluent.FluentTheme
import com.konyaco.fluent.component.TextField
import dev.faderainbow.yuanshen.ui.screen.setting.SettingViewModel
import dev.faderainbow.yuanshen.utils.helloWorldHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

/**
 *@author FadeRainbow
 *@date 2023/7/27
 *@time 20:19
 */
@Composable
fun HomeScreen(viewModel: HomeViewModel,settingViewModel:SettingViewModel) {
    /**
     * now only can show time
     */
    Column(
        modifier = Modifier.padding(50.dp)
    ) {
        Text(text = "HelloWorld", style = FluentTheme.typography.title)
        DisplayCurrentTime(settingViewModel.timeUpdateDelay.toLong(),settingViewModel)
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

        LaunchedEffect(Unit){
            delay(6000)
        }

    }
}
@Composable
private fun DisplayCurrentTime(delay:Long,viewModel: SettingViewModel) {
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
