@file:Suppress("UNUSED_EXPRESSION")

package client

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.*
import client.viewmodel.MainViewModel
import com.konyaco.fluent.FluentTheme
import com.konyaco.fluent.darkColors
import com.konyaco.fluent.lightColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


import ui.dialog.AntiDeathDialog
import ui.dialog.DialogView
import ui.dialog.ExitWarning
import ui.dialog.helper.viewmodel.AntiDeathViewModel
import ui.dialog.helper.viewmodel.ExitViewModel
import ui.view.helper.viewmodel.NekoViewModel

import windows.login.LoginWindow
import windows.login.viewmodel.LoginViewModel
import windows.login.viewmodel.UserViewModel
import windows.WindowViewModel
import windows.main.App
import windows.start.YuanShenWindow


fun main() = application {
    val viewModel = rememberSaveable { MainViewModel() }
    val exitViewModel = rememberSaveable { ExitViewModel() }
    val antiViewModel = rememberSaveable { AntiDeathViewModel() }
    val windowViewModel = rememberSaveable { WindowViewModel() }
    val loginViewModel = rememberSaveable { LoginViewModel() }
    val nekoViewModel = rememberSaveable { NekoViewModel() }
    val userViewModel = rememberSaveable { UserViewModel() }

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
                MainViewModel.ThemeMode.SYNC_SYSTEM -> if (isSystemInDarkTheme()) darkColors() else lightColors()
                MainViewModel.ThemeMode.DARK -> darkColors()
                MainViewModel.ThemeMode.LIGHT -> lightColors()

            }
             FluentTheme(colors = colors) {
                App(viewModel, nekoViewModel, loginViewModel, userViewModel)
                DialogView(viewModel)
                if (viewModel.enableAntiDeath) {
                    AntiDeathDialog(antiViewModel, exitViewModel)
                }
                ExitWarning(exitViewModel)
            }
        }
    LaunchedEffect(Unit){
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


