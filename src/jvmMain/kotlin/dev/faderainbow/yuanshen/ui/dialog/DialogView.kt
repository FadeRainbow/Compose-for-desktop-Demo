package dev.faderainbow.yuanshen.ui.dialog

import androidx.compose.runtime.Composable
import com.konyaco.fluent.component.Dialog
import com.konyaco.fluent.component.Text
import dev.faderainbow.yuanshen.windows.main.ClientViewModel


/**
 *@author FadeRainbow
 *@date 2023/7/28
 *@time 8:12
 */
@Composable
fun DialogView(viewModel: ClientViewModel) {
    Dialog(
        title = "Compose杂交试验田",
        cancelButtonText = "取消",
        onCancel = {viewModel.showDialog=false},
        confirmButtonText = "确认",
        onConfirm = {viewModel.showDialog=false},
        visible = viewModel.showDialog,
        content = {
            Text("这里是Compose生物杂交基地遵循我不知道法律")
        }
    )
}