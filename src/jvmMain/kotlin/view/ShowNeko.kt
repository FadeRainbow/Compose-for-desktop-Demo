package view

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
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
        tween(FluentDuration.ShortDuration, easing = FluentEasing.FadeInFadeOutEasing)
    )
    val alpha by animateFloatAsState(
        if(nekoViewModel.fadeMode==NekoViewModel.FadeMode.FADE_IN) 1f else 0f,
        tween(FluentDuration.VeryLongDuration, easing = FluentEasing.FadeInFadeOutEasing)
    )
        if(viewModel.screen==ViewModel.Screen.HOME){
            nekoViewModel.easingMode=NekoViewModel.EasingMode.IS_IN
            nekoViewModel.fadeMode=NekoViewModel.FadeMode.FADE_IN
        }else{
            nekoViewModel.easingMode=NekoViewModel.EasingMode.IS_OUT
            nekoViewModel.fadeMode=NekoViewModel.FadeMode.FADE_OUT
        }


    val nekoImage = when(nekoViewModel.girlType) {
        NekoViewModel.GirlType.NEKO -> painterResource("bluecat.png")
        NekoViewModel.GirlType.MAN -> painterResource("man.jpg")
    }
    Image(painter = nekoImage,contentDescription = "NekoGOD", alignment = Alignment.CenterEnd,modifier=Modifier.padding(end = width),alpha =alpha)

}
