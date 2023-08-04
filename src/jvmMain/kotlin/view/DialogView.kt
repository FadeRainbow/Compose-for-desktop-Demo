package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.konyaco.fluent.component.Dialog
import com.konyaco.fluent.component.Text

import viewmodel.ViewModel

/**
 *@author FadeRainbow
 *@date 2023/7/28
 *@time 8:12
 */
@Composable
fun DialogView(viewModel:ViewModel) {
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