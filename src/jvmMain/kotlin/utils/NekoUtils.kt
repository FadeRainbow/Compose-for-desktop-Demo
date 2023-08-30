package utils

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.view.ShowNeko
import ui.view.helper.viewmodel.NekoViewModel
import client.viewmodel.MainViewModel

/**
 *@author FadeRainbow
 *@date 2023/8/6
 *@time 11:19
 */
@Composable
fun DrawNeko(nekoViewModel: NekoViewModel, viewModel: MainViewModel){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement= Arrangement.Start){
        Column(
            modifier= Modifier.fillMaxHeight().padding(top=100.dp),
            verticalArrangement = Arrangement.Center
        ){
            ShowNeko(nekoViewModel,viewModel)
        }
    }
}