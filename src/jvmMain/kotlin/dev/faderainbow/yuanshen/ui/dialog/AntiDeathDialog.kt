package dev.faderainbow.yuanshen.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.konyaco.fluent.component.Dialog
import com.konyaco.fluent.component.Text
import kotlinx.coroutines.delay
import dev.faderainbow.yuanshen.ui.dialog.helper.viewmodel.AntiDeathViewModel
import dev.faderainbow.yuanshen.ui.dialog.helper.viewmodel.ExitViewModel
import java.time.LocalDateTime
import kotlin.system.exitProcess

/**
 *@author FadeRainbow
 *@date 2023/7/31
 *@time 8:49
 */
@Composable
fun AntiDeathDialog(viewModel: AntiDeathViewModel, exitViewModel: ExitViewModel){
    var currentTime = LocalDateTime.now()
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = LocalDateTime.now()
            delay(1000) // 每1000ms检查一次时间
        }
    }
    if(currentTime.hour == 22 && currentTime.minute == 0 && currentTime.second == 0){
        viewModel.showDialog22 = true
    }
    Dialog(
        title = "温馨提醒",
        onCancel = {
            viewModel.showDialog22=false
        },
        onConfirm = {
            exitViewModel.confirm =true
        },
        visible =viewModel.showDialog22,
        cancelButtonText = "了解",
        confirmButtonText = "现在就睡zzz",
        content = {
            Text("现在是北京时间22点休息一下吧! 0w0")
        }
    )
    if(currentTime.hour == 23 && currentTime.minute == 0 && currentTime.second == 0){
        viewModel.showDialog23 = true
    }
    Dialog(
        title = "温馨提醒",
        onCancel = {
            viewModel.showDialog23=false
        },
        onConfirm = {
            exitViewModel.confirm =true
        },
        visible =viewModel.showDialog23,
        cancelButtonText = "了解",
        confirmButtonText = "现在就睡zzz",
        content = {
            Text("现在是北京时间23点休息一下吧! 0w0")
        }
    )
    if(currentTime.hour == 24 && currentTime.minute == 0 && currentTime.second == 0){
        viewModel.showDialog24 = true
    }
    Dialog(
        title = "温馨提醒",
        onCancel = {
            exitViewModel.confirm =true
        },
        onConfirm = {
            exitViewModel.confirm =true
        },
        visible =viewModel.showDialog24,
        cancelButtonText = "BYD给我睡!",
        confirmButtonText = "现在就睡zzz",
        content = {
            Text("现在是北京时间24点休息一下吧! 0w0")
        }
    )
    if(currentTime.hour == 11 && currentTime.minute == 45 && currentTime.second == 14){
        viewModel.showDialogHomo = true
    }
    Dialog(
        title = "Homo慰问",
        onCancel = {
            exitProcess(1919810)
        },
        onConfirm = {
           exitProcess(114514)
        },
        visible =viewModel.showDialogHomo,
        cancelButtonText = "现在就端上来吧(迫不及待",
        confirmButtonText = "现在就端上来吧(迫不及待",
        content = {
            Text("是时候品尝美食了,吃完有奖励,没吃完有惩罚")
        }
    )
}