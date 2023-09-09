package dev.faderainbow.yuanshen.windows.main

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import dev.faderainbow.yuanshen.config.sidenav.SideNavRoute

import dev.faderainbow.yuanshen.ui.screen.home.HomeScreen
import dev.faderainbow.yuanshen.ui.screen.home.HomeViewModel
import dev.faderainbow.yuanshen.ui.screen.sakana.SakanaScreen
import dev.faderainbow.yuanshen.ui.screen.sakana.SakanaViewModel
import dev.faderainbow.yuanshen.ui.screen.setting.SettingScreen
import dev.faderainbow.yuanshen.ui.screen.setting.SettingViewModel
import dev.faderainbow.yuanshen.ui.screen.test.TestScreen
import dev.faderainbow.yuanshen.ui.screen.user.UserScreen
import dev.faderainbow.yuanshen.ui.view.helper.viewmodel.NekoViewModel
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
    settingViewModel:SettingViewModel,
    sakanaViewModel: SakanaViewModel
) {
    FrameViewBuilder(viewModel,nekoViewModel){
        when(viewModel.screen){
            SideNavRoute.Screen.HOME ->  HomeScreen(homeViewModel,settingViewModel)
            SideNavRoute.Screen.SETTING -> SettingScreen(settingViewModel,nekoViewModel)
            SideNavRoute.Screen.TEST -> TestScreen()
            SideNavRoute.Screen.USER -> UserScreen(loginViewModel,userViewModel)
            SideNavRoute.Screen.SAKANA -> SakanaScreen(sakanaViewModel)
        }
    }
}

