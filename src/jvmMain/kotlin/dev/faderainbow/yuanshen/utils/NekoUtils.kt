package dev.faderainbow.yuanshen.utils

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.faderainbow.yuanshen.ui.view.ShowNeko
import dev.faderainbow.yuanshen.ui.view.helper.viewmodel.NekoViewModel
import dev.faderainbow.yuanshen.windows.main.ClientViewModel


/**
 *@author FadeRainbow
 *@date 2023/8/6
 *@time 11:19
 */
@Composable
fun DrawNeko(nekoViewModel: NekoViewModel, viewModel: ClientViewModel){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement= Arrangement.Start){
        Column(
            modifier= Modifier.fillMaxHeight().padding(top=100.dp),
            verticalArrangement = Arrangement.Center
        ){
            ShowNeko(nekoViewModel,viewModel)
        }
    }
}