package windows

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import viewmodel.WindowViewModel
import androidx.compose.ui.window.*
import com.konyaco.fluent.background.Mica
import com.konyaco.fluent.component.Button
import com.konyaco.fluent.component.Icon
import com.konyaco.fluent.component.TextField
import view.ExitWarning
import viewmodel.ExitViewModel
import viewmodel.LoginViewModel
import viewmodel.ViewModel
import kotlin.system.exitProcess

/**
 *@author FadeRainbow
 *@date 2023/8/4
 *@time 10:40
 */
@Composable
private fun LoginView(viewModel:LoginViewModel,windowViewModel:WindowViewModel){
    Mica(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize()){
            Row(
                modifier =Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource("man.jpg"),
                    contentDescription = null,
                    modifier = Modifier.size(width = 100.dp, height = 100.dp).padding(top=5.dp).clip(RoundedCornerShape(300.dp)),
                )
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier =Modifier.fillMaxWidth()
            ){
                Icon(Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(30.dp).padding(start = 5.dp))
                TextField(
                modifier = Modifier.width(220.dp).padding(start =2.dp),
                value = viewModel.account,
                onValueChange = {viewModel.account = it},
                    )
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier =Modifier.fillMaxWidth()
            ){
                Icon(Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(30.dp).padding(start = 5.dp))
                TextField(
                    modifier = Modifier.width(220.dp).padding(start =2.dp),
                    value = viewModel.password,
                    onValueChange = {viewModel.password = it},
                )
            }
            Spacer(Modifier.height(25.dp))
            Row(
                modifier =Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    modifier = Modifier.width(100.dp),
                    onClick = {
                        if (
                            viewModel.password.text.isEmpty()
                            || viewModel.account.text.isEmpty()
                        ) {
                            viewModel.showDialog = true
                        } else {
                            windowViewModel.showLoginWindow = false
                            windowViewModel.showClientWindow = true
                        }
                    },
                    iconOnly = true
                ){
                    Text(text ="登入")
                }
            }
        }
    }
        com.konyaco.fluent.component.Dialog(
            title = "您的账户或密码为空",
            cancelButtonText = "了解",
            onCancel = { viewModel.showDialog = false },
            confirmButtonText = "了解",
            onConfirm = { viewModel.showDialog = false },
            visible = viewModel.showDialog,
            content = {}
        )
}
@Composable
fun LoginWindow(
    windowViewModel:WindowViewModel
    ,loginViewModel:LoginViewModel,
    exitViewModel: ExitViewModel
)
{
    Window(
        onCloseRequest = { exitViewModel.showDialog = true},
        state = rememberWindowState(
            position = WindowPosition(Alignment.Center),
            width = 300.dp, height = 400.dp
        ),
        visible = windowViewModel.showLoginWindow,
        title = "迷你世界登录窗口"
        ){
        if (exitViewModel.confirm) {
           exitProcess(114514)
        }
        ExitWarning(exitViewModel)
        LoginView(loginViewModel,windowViewModel)
    }
}