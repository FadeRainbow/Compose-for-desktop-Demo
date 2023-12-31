package dev.faderainbow.yuanshen.windows.login.viewmodel
import androidx.compose.runtime.*
import androidx.compose.runtime.collection.mutableVectorOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.*
import java.lang.Thread.sleep
import java.time.LocalTime
import kotlin.concurrent.thread
/**
 *@author FadeRainbow
 *@date 2023/8/4
 *@time 12:41
 */
class LoginViewModel {
    var account by mutableStateOf(TextFieldValue())
    var password by mutableStateOf(TextFieldValue())
    var showDialog by mutableStateOf(false)
    var isHovered by  mutableStateOf(false)
    var isHovered2 by  mutableStateOf(false)
    var isHovered3 by  mutableStateOf(false)

}