package view

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.konyaco.fluent.animation.FluentDuration
import com.konyaco.fluent.animation.FluentEasing
import kotlinx.coroutines.delay
import viewmodel.NekoViewModel
import viewmodel.ViewModel
import java.lang.Thread.sleep
import kotlin.concurrent.thread

/**
 *@author FadeRainbow
 *@date 2023/8/6
 *@time 7:22
 */
@Composable
 fun ShowNeko(nekoViewModel: NekoViewModel, viewModel: ViewModel){
    val width by animateDpAsState(
        if (nekoViewModel.easingMode==NekoViewModel.EasingMode.IS_IN) 600.dp else 800.dp,
        tween(FluentDuration.ShortDuration, easing = FluentEasing.FastInvokeEasing)
    )
        if(viewModel.screen==ViewModel.Screen.HOME){
            nekoViewModel.easingMode=NekoViewModel.EasingMode.IS_IN
        }else{
            nekoViewModel.easingMode=NekoViewModel.EasingMode.IS_OUT
        }


    val nekoImage = painterResource("bluecat.png")
    Image(painter = nekoImage, contentDescription = "NekoGOD", alignment = Alignment.CenterEnd,modifier=Modifier.padding(end = width))

}
