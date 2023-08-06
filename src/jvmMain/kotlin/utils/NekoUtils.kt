package utils

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import view.ShowNeko
import viewmodel.NekoViewModel
import viewmodel.ViewModel

/**
 *@author FadeRainbow
 *@date 2023/8/6
 *@time 11:19
 */
@Composable
fun DrawNeko(nekoViewModel: NekoViewModel, viewModel: ViewModel){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement= Arrangement.Start){
        Column(
            modifier= Modifier.fillMaxHeight().padding(top=100.dp),
            verticalArrangement = Arrangement.Center
        ){
            ShowNeko(nekoViewModel,viewModel)
        }
    }
}