package ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.konyaco.fluent.background.Layer
import com.konyaco.fluent.component.Icon
import com.konyaco.fluent.component.Switcher
import com.konyaco.fluent.component.Text
import windows.login.viewmodel.LoginViewModel
import windows.login.viewmodel.UserViewModel

@Composable
fun UserScreen(information: LoginViewModel, viewModel: UserViewModel){
    Row(
        modifier =Modifier.fillMaxWidth()
    ){
        Layer (
            modifier = Modifier.width(350.dp).height(90.dp).padding(top=10.dp,start=3.dp),
            shape = RoundedCornerShape(8),
            cornerRadius = 8.dp,
            outsideBorder = true
        ){
            Row{
                Image(
                    painter = painterResource("man.jpg"),
                    contentDescription = null,
                    modifier = Modifier.size(width = 100.dp, height = 100.dp).padding(top=3.dp).clip(RoundedCornerShape(500.dp)),
                )
                Column(
                    modifier=Modifier.padding(top=2.dp,start=2.dp)
                ){
                    Row{
                        Icon(Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(18.dp).padding(top=1.dp))
                        Spacer(modifier=Modifier.padding(start=1.dp))
                        Text("账户 ${accountLengthChecker(information.account.text)}")
                    }
                    Spacer(modifier =Modifier.padding(2.dp))
                    var passwordStr=""
                    Row{
                        if(viewModel.hide){
                            passwordStr="*".repeat(information.password.text.length)
                        }else{
                            passwordStr=information.password.text
                        }
                        Icon(Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(18.dp).padding(top=1.dp))
                        Spacer(modifier=Modifier.padding(start=1.dp))
                        Text("密码 $passwordStr")
                    }
                    Switcher(
                        checked = viewModel.hide,
                        onCheckStateChange = {viewModel.hide=it},
                        textBefore = true,
                        text = if(viewModel.hide)"隐藏" else "显示"
                    )
                }
            }
        }
    }
}
private fun accountLengthChecker(account:String): String {
    if (account.length>=12){
        return "${account.subSequence(0,12)}..."
    }
    return account
}