package viewmodel
import androidx.compose.runtime.*
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
}