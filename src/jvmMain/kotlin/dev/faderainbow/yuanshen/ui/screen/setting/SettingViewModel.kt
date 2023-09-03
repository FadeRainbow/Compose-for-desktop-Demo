package dev.faderainbow.yuanshen.ui.screen.setting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.faderainbow.yuanshen.windows.main.ClientViewModel

/**
 *@author FadeRainbow
 *@date 2023/9/3
 *@time 11:04
 */
class SettingViewModel: ClientViewModel() {
    var timeUpdateDelay by mutableStateOf(500f)
    var enableAntiDeath by mutableStateOf(true)
    var exactTime by mutableStateOf(true)
}