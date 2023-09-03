package dev.faderainbow.yuanshen.windows.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.faderainbow.yuanshen.config.sidenav.SideNavRoute

/**
 *@author FadeRainbow
 *@date 2023/9/3
 *@time 10:50
 */
open class ClientViewModel {
    var screen by mutableStateOf(SideNavRoute.Screen.HOME)
    var expanded by mutableStateOf(true)
    var theme by mutableStateOf(SideNavRoute.ThemeMode.SYNC_SYSTEM)

    /**
     * this dialog is startClient welcome
     */
    var showDialog by mutableStateOf(true)
}