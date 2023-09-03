package dev.faderainbow.yuanshen.windows.main

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.konyaco.fluent.background.Layer
import com.konyaco.fluent.background.Mica
import com.konyaco.fluent.component.SideNav
import com.konyaco.fluent.component.SideNavItem
import com.konyaco.fluent.icons.Icons
import com.konyaco.fluent.icons.regular.DeveloperBoard
import dev.faderainbow.yuanshen.config.sidenav.SideNavItemList
import dev.faderainbow.yuanshen.config.sidenav.SideNavRoute
import dev.faderainbow.yuanshen.ui.screen.home.HomeScreen
import dev.faderainbow.yuanshen.ui.screen.home.HomeViewModel
import dev.faderainbow.yuanshen.ui.screen.setting.SettingScreen
import dev.faderainbow.yuanshen.ui.screen.setting.SettingViewModel
import dev.faderainbow.yuanshen.ui.screen.test.TestScreen
import dev.faderainbow.yuanshen.ui.screen.ultraman.UltramanScreen
import dev.faderainbow.yuanshen.ui.screen.user.UserScreen
import dev.faderainbow.yuanshen.ui.view.helper.viewmodel.NekoViewModel
import dev.faderainbow.yuanshen.utils.DrawNeko
import dev.faderainbow.yuanshen.windows.login.viewmodel.LoginViewModel
import dev.faderainbow.yuanshen.windows.login.viewmodel.UserViewModel

/**
 *@author FadeRainbow
 *@date 2023/8/31
 *@time 9:23
 */
@Composable
@Preview
        /**
         *this is main about client,show SideNav and some item to show screen(s)
         */
fun App(
    viewModel:ClientViewModel,
    nekoViewModel: NekoViewModel,
    loginViewModel: LoginViewModel,
    userViewModel: UserViewModel,
    homeViewModel:HomeViewModel,
    settingViewModel:SettingViewModel
) {
    Mica(
        modifier = Modifier.fillMaxSize()
    ){//填充背景
        Row(
            modifier= Modifier.fillMaxSize()
        ) {
            SideNav(
                modifier = Modifier.fillMaxHeight(),
                expanded = viewModel.expanded,
                onExpandStateChange = {
                    viewModel.expanded =it
                },
                title = { Text("侧导栏") },
                footer = {
                    SideNavItem(
                        selected =  viewModel.screen== SideNavRoute.Screen.TEST,
                        onClick = { viewModel.screen= SideNavRoute.Screen.TEST},
                        icon = { Icon(Icons.Default.DeveloperBoard,contentDescription = null) }
                    ){
                        Text(text ="Test")
                        //实验
                    }
                }
            ){
                /**
                 * this in config
                 */
                SideNavItemList(viewModel)

            }
            Layer(//另一层
                modifier = Modifier.weight(1f).fillMaxHeight().padding(top = 10.dp,end = 5.dp),
                outsideBorder = true,
                shape = RoundedCornerShape(8.dp),
                cornerRadius = 8.dp
            ){
                when(viewModel.screen){
                    SideNavRoute.Screen.HOME ->  HomeScreen(homeViewModel,settingViewModel)
                    SideNavRoute.Screen.SETTING -> SettingScreen(settingViewModel,nekoViewModel)
                    SideNavRoute.Screen.TEST -> TestScreen()
                    SideNavRoute.Screen.USER -> UserScreen(loginViewModel,userViewModel)
                    SideNavRoute.Screen.ULTRAMAN -> UltramanScreen()
                    }
                }
            }
        }
        DrawNeko(nekoViewModel,viewModel)
    }
