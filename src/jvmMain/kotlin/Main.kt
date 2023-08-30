import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import com.konyaco.fluent.FluentTheme
import com.konyaco.fluent.background.Layer
import com.konyaco.fluent.background.Mica
import com.konyaco.fluent.component.SideNav
import com.konyaco.fluent.component.SideNavItem
import com.konyaco.fluent.darkColors
import com.konyaco.fluent.icons.Icons
import com.konyaco.fluent.icons.regular.*
import com.konyaco.fluent.lightColors


import screen.HomeScreen
import screen.SettingScreen
import screen.TestScreen
import screen.UserScreen
import utils.DrawNeko
import view.AntiDeathDialog
import view.DialogView
import view.ExitWarning
import view.ShowNeko
import viewmodel.*
import windows.LoginWindow
import java.lang.Thread.sleep


@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App(viewModel:ViewModel,nekoViewModel:NekoViewModel,loginViewModel:LoginViewModel,userViewModel:UserViewModel) {
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
                        selected =  viewModel.screen==ViewModel.Screen.TEST,
                        onClick = {viewModel.screen=ViewModel.Screen.TEST},
                        icon = {Icon(Icons.Default.DeveloperBoard,contentDescription = null)}
                    ){
                        Text(text ="Test")
                        //实验
                    }
                }
            ){
                SideNavItem(
                    selected = viewModel.screen ==ViewModel.Screen.USER,
                    onClick = {
                        viewModel.screen =ViewModel.Screen.USER
                    },
                    icon = { Icon(Icons.Default.TabInprivateAccount,contentDescription = null) }
                ){
                    Text("用户信息")
                }
                SideNavItem(
                    selected = viewModel.screen ==ViewModel.Screen.HOME,
                    onClick = {
                        viewModel.screen =ViewModel.Screen.HOME
                              },
                    icon = { Icon(Icons.Default.Home,contentDescription = null) }
                ){
                    Text("老玩家")
                }
                SideNavItem(
                    selected = viewModel.screen ==ViewModel.Screen.SETTING,  //控制扩展页是否开启
                    onClick = {
                        viewModel.screen =ViewModel.Screen.SETTING
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
                ViewModel.Screen.HOME ->  HomeScreen(viewModel,nekoViewModel)
                ViewModel.Screen.SETTING -> SettingScreen(viewModel,nekoViewModel)
                ViewModel.Screen.TEST -> TestScreen(viewModel)
                ViewModel.Screen.USER -> UserScreen(loginViewModel,userViewModel)
            }
           }
    }
        DrawNeko(nekoViewModel,viewModel)
    }
}




fun main() = application {
    val viewModel = rememberSaveable { ViewModel() }
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
                ViewModel.ThemeMode.SYNC_SYSTEM -> if (isSystemInDarkTheme()) darkColors() else lightColors()
                ViewModel.ThemeMode.DARK -> darkColors()
                ViewModel.ThemeMode.LIGHT -> lightColors()

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

