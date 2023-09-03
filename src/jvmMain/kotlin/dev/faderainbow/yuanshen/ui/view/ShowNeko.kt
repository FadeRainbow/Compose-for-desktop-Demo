package dev.faderainbow.yuanshen.ui.view

//import androidx.compose.ui.text.style.TextForegroundStyle.Unspecified.alpha
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.konyaco.fluent.animation.FluentDuration
import com.konyaco.fluent.animation.FluentEasing
import dev.faderainbow.yuanshen.config.NekoEasingType
import dev.faderainbow.yuanshen.config.sidenav.SideNavRoute
import dev.faderainbow.yuanshen.ui.view.helper.viewmodel.NekoViewModel
import dev.faderainbow.yuanshen.windows.main.ClientViewModel

/**
 *@author FadeRainbow
 *@date 2023/8/6
 *@time 7:22
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
 fun ShowNeko(nekoViewModel: NekoViewModel, viewModel: ClientViewModel){
    val width by animateDpAsState(
        if (nekoViewModel.easingMode== NekoEasingType.EasingMode.IS_IN) 600.dp else 800.dp,
        tween(FluentDuration.ShortDuration, easing = FluentEasing.FadeInFadeOutEasing)
    )
    val alpha by animateFloatAsState(
        if(nekoViewModel.fadeMode== NekoEasingType.FadeMode.FADE_IN) 1f else 0f,
        tween(FluentDuration.VeryLongDuration, easing = FluentEasing.FadeInFadeOutEasing)
    )
        if(viewModel.screen== SideNavRoute.Screen.HOME){
            nekoViewModel.easingMode= NekoEasingType.EasingMode.IS_IN
            nekoViewModel.fadeMode= NekoEasingType.FadeMode.FADE_IN
        }else{
            nekoViewModel.easingMode= NekoEasingType.EasingMode.IS_OUT
            nekoViewModel.fadeMode= NekoEasingType.FadeMode.FADE_OUT
            if(viewModel.screen== SideNavRoute.Screen.USER){
                nekoViewModel.easingMode= NekoEasingType.EasingMode.IS_IN
                nekoViewModel.fadeMode= NekoEasingType.FadeMode.FADE_IN
            }else{
                nekoViewModel.easingMode= NekoEasingType.EasingMode.IS_OUT
                nekoViewModel.fadeMode= NekoEasingType.FadeMode.FADE_OUT
            }
        }
//     var visible by remember { mutableStateOf(false) }
//     LaunchedEffect(Unit){
//         delay(1500)
//         visible=true
//     }
    val nekoImage = when(nekoViewModel.girlType) {
        NekoEasingType.GirlType.NEKO -> painterResource("bluecat.png")
        NekoEasingType.GirlType.MAN -> painterResource("man.jpg")
    }
//    AnimatedVisibility(
//        visible=visible,
//        enter = expandHorizontally(expandFrom = Alignment.Start)+ fadeIn(tween(FluentDuration.LongDuration), initialAlpha = 0.1f),
//    ){
//        Image(painter = nekoImage,contentDescription = "NekoGOD")
//    }


    Image(painter = nekoImage,contentDescription = "NekoGOD", alignment = Alignment.CenterEnd,modifier=Modifier.padding(end = width),alpha =alpha)

}
