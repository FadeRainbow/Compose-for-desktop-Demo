package screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.konyaco.fluent.FluentTheme
import com.konyaco.fluent.background.Layer
import com.konyaco.fluent.background.Mica
import com.konyaco.fluent.component.CheckBox
import com.konyaco.fluent.component.Slider
import com.konyaco.fluent.component.Switcher
import com.konyaco.fluent.component.Text

import viewmodel.ViewModel

/**
 *@author FadeRainbow
 *@date 2023/7/27
 *@time 20:18
 */
@Composable
fun SettingScreen(viewModel: ViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Layer(
            modifier =Modifier.size(140.dp).padding(start =2.dp,top=2.dp),
            shape = RoundedCornerShape(8),
            cornerRadius = 8.dp,
            outsideBorder = true
        ){
            Column(
                modifier = Modifier.fillMaxHeight().padding(start = 15.dp, top = 20.dp)
            ) {
                Text(text = "主题选择", style = FluentTheme.typography.body)
                CheckBox(
                    modifier = Modifier.padding(5.dp),
                    checked = (viewModel.theme==ViewModel.ThemeMode.SYNC_SYSTEM),
                    label = "同步系统",
                    onCheckStateChange = {viewModel.theme=ViewModel.ThemeMode.SYNC_SYSTEM},
                )
                CheckBox(
                    modifier = Modifier.padding(5.dp),
                    checked = (viewModel.theme==ViewModel.ThemeMode.DARK),
                    label = "黑暗主♂题",
                    onCheckStateChange = {viewModel.theme=ViewModel.ThemeMode.DARK},
                )
                CheckBox(
                    modifier = Modifier.padding(5.dp),
                    checked = (viewModel.theme==ViewModel.ThemeMode.LIGHT),
                    label = "亮涩主题",
                    onCheckStateChange = {viewModel.theme=ViewModel.ThemeMode.LIGHT},
                )
            }
        }
        Layer(
            modifier =Modifier.height(140.dp).width(270.dp).padding(start =2.dp,top=2.dp),
            shape = RoundedCornerShape(8),
            cornerRadius = 8.dp,
            outsideBorder = true
        ){
            Column( modifier = Modifier.fillMaxHeight().padding(start = 15.dp, top = 20.dp)){
                Text(text = "时间设置", style = FluentTheme.typography.body)
                Row{
                    Text("刷新延迟")
                    Slider(
                        modifier = Modifier.width(140.dp).height(20.dp),
                        value = viewModel.timeUpdateDelay,
                        onValueChange = {viewModel.timeUpdateDelay= it},
                        steps = 10,
                        valueRange = 0f..1000f
                    )
                    Layer(modifier = Modifier.height(20.dp).width(70.dp)){
                        Text("${viewModel.timeUpdateDelay.toInt()}MS")
                    }
                }
                Row{
                    Text("精确时间")
                    Spacer(Modifier.width(5.dp))
                    Switcher(
                        checked = viewModel.exactTime,
                        onCheckStateChange = {viewModel.exactTime = it}
                        )
                }
                Spacer(Modifier.height(3.dp))
                Row{
                    Text("熬夜警告")
                    Spacer(Modifier.width(5.dp))
                    Switcher(
                        checked = viewModel.enableAntiDeath,
                        onCheckStateChange = {viewModel.enableAntiDeath = it}
                    )
                }
            }
        }
    }
}