package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 *@author FadeRainbow
 *@date 2023/8/6
 *@time 7:25
 */
class NekoViewModel {
    var easingMode by mutableStateOf(EasingMode.IS_OUT)
    enum class EasingMode{
        IS_IN,IS_OUT
    }
}
