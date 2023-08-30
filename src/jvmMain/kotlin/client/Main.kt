package client

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import client.viewmodel.MainViewModel
import com.konyaco.fluent.FluentTheme
import com.konyaco.fluent.background.Layer
import com.konyaco.fluent.background.Mica
import com.konyaco.fluent.component.SideNav
import com.konyaco.fluent.component.SideNavItem
import com.konyaco.fluent.darkColors
import com.konyaco.fluent.icons.Icons
import com.konyaco.fluent.icons.regular.*
import com.konyaco.fluent.lightColors


import ui.screen.HomeScreen
import ui.screen.SettingScreen
import ui.screen.TestScreen
import ui.screen.UserScreen
import utils.DrawNeko
import ui.dialog.AntiDeathDialog
import ui.dialog.DialogView
import ui.dialog.ExitWarning
import ui.dialog.helper.viewmodel.AntiDeathViewModel
import ui.dialog.helper.viewmodel.ExitViewModel
import ui.view.helper.viewmodel.NekoViewModel

import windows.login.LoginWindow
import windows.login.viewmodel.LoginViewModel
import windows.login.viewmodel.UserViewModel
import windows.login.viewmodel.WindowViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
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
                title = {Text("侧导栏")},
                footer = {
                    SideNavItem(
                        selected =  viewModel.screen== MainViewModel.Screen.TEST,
                        onClick = {viewModel.screen= MainViewModel.Screen.TEST},
                        icon = {Icon(Icons.Default.DeveloperBoard,contentDescription = null)}
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
                MainViewModel.Screen.HOME ->  HomeScreen(viewModel,nekoViewModel)
                MainViewModel.Screen.SETTING -> SettingScreen(viewModel,nekoViewModel)
                MainViewModel.Screen.TEST -> TestScreen(viewModel)
                MainViewModel.Screen.USER -> UserScreen(loginViewModel,userViewModel)
            }
           }
    }
        DrawNeko(nekoViewModel,viewModel)
    }
}




fun main() = application {
    val viewModel = rememberSaveable { MainViewModel() }
    val exitViewModel = rememberSaveable { ExitViewModel() }
    val antiViewModel = rememberSaveable { AntiDeathViewModel() }
    val windowViewModel = rememberSaveable { WindowViewModel() }
    val loginViewModel = rememberSaveable { LoginViewModel() }
    val nekoViewModel = rememberSaveable { NekoViewModel() }
    val userViewModel = rememberSaveable { UserViewModel() }
    //窗口
    LoginWindow(windowViewModel,loginViewModel,exitViewModel)

        Window(//MainWindow
            onCloseRequest = {
                exitViewModel.showDialog = true
            },
            state = rememberWindowState(position = WindowPosition(Alignment.Center)),
            title = "王者荣耀:刺激战场",
            visible = windowViewModel.showClientWindow
        ) {

            if (exitViewModel.confirm) {
                exitApplication()
            }
            val colors = when (viewModel.theme) {
                MainViewModel.ThemeMode.SYNC_SYSTEM -> if (isSystemInDarkTheme()) darkColors() else lightColors()
                MainViewModel.ThemeMode.DARK -> darkColors()
                MainViewModel.ThemeMode.LIGHT -> lightColors()

            }
            FluentTheme(
                colors = colors
            ) {
                App(viewModel,nekoViewModel,loginViewModel,userViewModel)
                DialogView(viewModel)
                if (viewModel.enableAntiDeath) {
                    AntiDeathDialog(antiViewModel, exitViewModel)
                }
                ExitWarning(exitViewModel)
            }
        }
    }

