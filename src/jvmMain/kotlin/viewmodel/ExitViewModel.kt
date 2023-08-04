package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 *@author FadeRainbow
 *@date 2023/7/29
 *@time 8:27
 */
class ExitViewModel {
    var cancel by mutableStateOf(false)
    var confirm by mutableStateOf(false)
    var showDialog by mutableStateOf(false)
}