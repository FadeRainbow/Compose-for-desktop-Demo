package dev.faderainbow.yuanshen.ui.dialog

import androidx.compose.runtime.Composable
import com.konyaco.fluent.component.Dialog
import dev.faderainbow.yuanshen.ui.dialog.helper.viewmodel.ExitViewModel

/**
 *@author FadeRainbow
 *@date 2023/7/29
 *@time 8:23
 */
@Composable
fun ExitWarning(viewModel: ExitViewModel){
    Dialog(
        title = "您真的要退出吗?",
        visible = viewModel.showDialog,
        content = {},
        cancelButtonText = "取消",
        onCancel = {viewModel.showDialog=false},
        confirmButtonText = "确认",
        onConfirm = {viewModel.confirm=true},
    )
}