package dev.faderainbow.yuanshen.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue

/**
 *@author FadeRainbow
 *@date 2023/9/3
 *@time 11:14
 */
class HomeViewModel {
    var text by mutableStateOf(TextFieldValue())
}