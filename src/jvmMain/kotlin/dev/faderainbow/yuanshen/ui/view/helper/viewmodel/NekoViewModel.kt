package dev.faderainbow.yuanshen.ui.view.helper.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.faderainbow.yuanshen.config.NekoEasingType

/**
 *@author FadeRainbow
 *@date 2023/8/6
 *@time 7:25
 */
class NekoViewModel {
    var easingMode by mutableStateOf(NekoEasingType.EasingMode.IS_OUT)
    var fadeMode by mutableStateOf(NekoEasingType.FadeMode.FADE_OUT)
    var girlType by mutableStateOf(NekoEasingType.GirlType.NEKO)

}
