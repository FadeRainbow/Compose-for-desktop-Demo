package windows.main

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
import client.viewmodel.MainViewModel
import com.konyaco.fluent.background.Layer
import com.konyaco.fluent.background.Mica
import com.konyaco.fluent.component.SideNav
import com.konyaco.fluent.component.SideNavItem
import com.konyaco.fluent.icons.Icons
import com.konyaco.fluent.icons.regular.DeveloperBoard
import com.konyaco.fluent.icons.regular.Home
import com.konyaco.fluent.icons.regular.Settings
import com.konyaco.fluent.icons.regular.TabInprivateAccount
import ui.screen.HomeScreen
import ui.screen.SettingScreen
import ui.screen.TestScreen
import ui.screen.UserScreen
import ui.view.helper.viewmodel.NekoViewModel
import utils.DrawNeko
import windows.login.viewmodel.LoginViewModel
import windows.login.viewmodel.UserViewModel

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
fun App(viewModel: MainViewModel, nekoViewModel: NekoViewModel, loginViewModel: LoginViewModel, userViewModel: UserViewModel) {
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
                        selected =  viewModel.screen== MainViewModel.Screen.TEST,
                        onClick = {viewModel.screen= MainViewModel.Screen.TEST},
                        icon = { Icon(Icons.Default.DeveloperBoard,contentDescription = null) }
                    ){
                        Text(text ="Test")
                        //实验
                    }
                }
            ){
                SideNavItem(
                    selected = viewModel.screen == MainViewModel.Screen.USER,
                    onClick = {
                        viewModel.screen = MainViewModel.Screen.USER
                    },
                    icon = { Icon(Icons.Default.TabInprivateAccount,contentDescription = null) }
                ){
                    Text("用户信息")
                }
                SideNavItem(
                    selected = viewModel.screen == MainViewModel.Screen.HOME,
                    onClick = {
                        viewModel.screen = MainViewModel.Screen.HOME
                    },
                    icon = { Icon(Icons.Default.Home,contentDescription = null) }
                ){
                    Text("老玩家")
                }
                SideNavItem(
                    selected = viewModel.screen == MainViewModel.Screen.SETTING,  //控制扩展页是否开启
                    onClick = {
                        viewModel.screen = MainViewModel.Screen.SETTING
                    },
                    icon = { Icon(Icons.Default.Settings,contentDescription = null) }
                ){
                    Text("射置")
                }
            }
            Layer(//另一层
                modifier = Modifier.weight(1f).fillMaxHeight().padding(top = 10.dp,end = 5.dp),
                outsideBorder = true,
                shape = RoundedCornerShape(8.dp),
                cornerRadius = 8.dp
            ){
                when(viewModel.screen){
                    MainViewModel.Screen.HOME ->  HomeScreen(viewModel)
                    MainViewModel.Screen.SETTING -> SettingScreen(viewModel,nekoViewModel)
                    MainViewModel.Screen.TEST -> TestScreen(viewModel)
                    MainViewModel.Screen.USER -> UserScreen(loginViewModel,userViewModel)
                }
            }
        }
        DrawNeko(nekoViewModel,viewModel)
    }
}