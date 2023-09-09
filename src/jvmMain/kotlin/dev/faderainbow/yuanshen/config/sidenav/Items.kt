package dev.faderainbow.yuanshen.config.sidenav

import androidx.compose.runtime.Composable
import com.konyaco.fluent.component.Icon
import com.konyaco.fluent.component.SideNavItem
import com.konyaco.fluent.component.Text
import com.konyaco.fluent.icons.Icons
import com.konyaco.fluent.icons.regular.FoodFish
import com.konyaco.fluent.icons.regular.Home
import com.konyaco.fluent.icons.regular.Settings
import com.konyaco.fluent.icons.regular.TabInprivateAccount
import dev.faderainbow.yuanshen.windows.main.ClientViewModel


/**
 *@author FadeRainbow
 *@date 2023/9/3
 *@time 10:23
 */
    @Composable
    fun SideNavItemList(viewModel: ClientViewModel){
        SideNavItem(
            selected = viewModel.screen == SideNavRoute.Screen.USER,
            onClick = {
                viewModel.screen = SideNavRoute.Screen.USER
            },
            icon = { Icon(Icons.Default.TabInprivateAccount, contentDescription = null) }
        ) {
            Text("用户信息")
        }
        SideNavItem(
            selected = viewModel.screen == SideNavRoute.Screen.HOME,
            onClick = {
                viewModel.screen = SideNavRoute.Screen.HOME
            },
            icon = { Icon(Icons.Default.Home, contentDescription = null) }
        ) {
            Text("老玩家")
        }
        SideNavItem(
            selected = viewModel.screen == SideNavRoute.Screen.SETTING,  //控制扩展页是否开启
            onClick = {
                viewModel.screen = SideNavRoute.Screen.SETTING
            },
            icon = { Icon(Icons.Default.Settings, contentDescription = null) }
        ) {
            Text("射置")
        }
        SideNavItem(
            selected = viewModel.screen == SideNavRoute.Screen.SAKANA,
            onClick = {
                viewModel.screen = SideNavRoute.Screen.SAKANA
            },
            icon = { Icon(Icons.Default.FoodFish, contentDescription = null) }
        ) {
            Text("Sakana")
        }
}