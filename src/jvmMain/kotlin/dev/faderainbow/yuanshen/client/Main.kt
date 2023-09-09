@file:Suppress("UNUSED_EXPRESSION")

package dev.faderainbow.yuanshen.client


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.konyaco.fluent.FluentTheme
import com.konyaco.fluent.darkColors
import com.konyaco.fluent.lightColors
import dev.faderainbow.yuanshen.config.sidenav.SideNavRoute
import dev.faderainbow.yuanshen.ui.dialog.AntiDeathDialog
import dev.faderainbow.yuanshen.ui.dialog.DialogView
import dev.faderainbow.yuanshen.ui.dialog.ExitWarning
import dev.faderainbow.yuanshen.ui.dialog.helper.viewmodel.AntiDeathViewModel
import dev.faderainbow.yuanshen.ui.dialog.helper.viewmodel.ExitViewModel
import dev.faderainbow.yuanshen.ui.screen.home.HomeViewModel
import dev.faderainbow.yuanshen.ui.screen.sakana.SakanaViewModel
import dev.faderainbow.yuanshen.ui.screen.setting.SettingViewModel
import dev.faderainbow.yuanshen.ui.view.helper.viewmodel.NekoViewModel
import dev.faderainbow.yuanshen.windows.WindowViewModel
import dev.faderainbow.yuanshen.windows.login.LoginWindow
import dev.faderainbow.yuanshen.windows.login.viewmodel.LoginViewModel
import dev.faderainbow.yuanshen.windows.login.viewmodel.UserViewModel
import dev.faderainbow.yuanshen.windows.main.App
import dev.faderainbow.yuanshen.windows.main.ClientViewModel
import dev.faderainbow.yuanshen.windows.start.YuanShenWindow
import kotlinx.coroutines.delay


fun main() = application {
    /**
     * u need know ->
     * this project is MVVM{shit code}
     *
     * this about start client, some viewModel init
     * made by FadeRainbow
     * github: https://github.com/FadeRainbow/Compose-for-desktop-Demo
     *
     * my second project about compose for desktop
     *
     */
    val viewModel = rememberSaveable { ClientViewModel() }
    val exitViewModel = rememberSaveable { ExitViewModel() }
    val antiViewModel = rememberSaveable { AntiDeathViewModel() }
    val windowViewModel = rememberSaveable { WindowViewModel() }
    val loginViewModel = rememberSaveable { LoginViewModel() }
    val nekoViewModel = rememberSaveable { NekoViewModel() }
    val userViewModel = rememberSaveable { UserViewModel() }
    val settingViewModel = rememberSaveable { SettingViewModel() }
    val homeViewModel = rememberSaveable { HomeViewModel() }
    val sakanaViewModel = rememberSaveable{ SakanaViewModel() }
        //实例化
    val op = YuanShenWindow(windowViewModel)
    val login = LoginWindow(windowViewModel,loginViewModel,exitViewModel)
    val client=  Window(//MainWindow
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
                SideNavRoute.ThemeMode.SYNC_SYSTEM -> if (isSystemInDarkTheme()) darkColors() else lightColors()
                SideNavRoute.ThemeMode.DARK -> darkColors()
                SideNavRoute.ThemeMode.LIGHT -> lightColors()

            }
             FluentTheme(colors = colors) {
                App(
                    viewModel = viewModel,
                    nekoViewModel = nekoViewModel,
                    loginViewModel = loginViewModel,
                    userViewModel = userViewModel,
                    homeViewModel = homeViewModel,
                    settingViewModel = settingViewModel,
                    sakanaViewModel = sakanaViewModel

                )
                DialogView(viewModel)
                if (settingViewModel.enableAntiDeath) {
                    AntiDeathDialog(antiViewModel, exitViewModel)
                }
                ExitWarning(exitViewModel)

            }
        }
    LaunchedEffect(Unit){
        /**
         * load some modules
         * delay5000 to waiting
         */
        op
        delay(5000)
        windowViewModel.showYuanShenWindow=false
        windowViewModel.showLoginWindow=true
        run {
            login
            client
        }
    }
    }


