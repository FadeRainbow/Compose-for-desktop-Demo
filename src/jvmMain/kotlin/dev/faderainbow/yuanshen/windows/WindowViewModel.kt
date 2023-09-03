package dev.faderainbow.yuanshen.windows

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 *@author FadeRainbow
 *@date 2023/8/4
 *@time 10:41
 */
class WindowViewModel {
    var showClientWindow by mutableStateOf(false)
    var showLoginWindow by mutableStateOf(false)
    var showYuanShenWindow by mutableStateOf(true)
}