package viewmodel

import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.*
import java.lang.Thread.sleep
import java.time.LocalTime
import kotlin.concurrent.thread


/**
 *@author FadeRainbow
 *@date 2023/7/27
 *@time 20:17
 */
 class ViewModel {
    var screen by mutableStateOf(Screen.HOME)
    var expanded by mutableStateOf(true)
    var theme by mutableStateOf(ThemeMode.SYNC_SYSTEM)
    var text by mutableStateOf(TextFieldValue())
    var showDialog by mutableStateOf(true)
    var timeUpdateDelay by mutableStateOf(1000f)
    var exactTime by mutableStateOf(true)
    var enableAntiDeath by mutableStateOf(true)
    enum class Screen {
        HOME, SETTING, TEST,USER
    }

    enum class ThemeMode {
        SYNC_SYSTEM, DARK, LIGHT
    }
}