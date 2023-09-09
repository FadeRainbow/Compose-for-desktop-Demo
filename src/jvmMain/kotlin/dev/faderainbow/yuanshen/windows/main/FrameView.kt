package dev.faderainbow.yuanshen.windows.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.konyaco.fluent.background.Layer
import com.konyaco.fluent.background.Mica
import com.konyaco.fluent.component.SideNav
import com.konyaco.fluent.component.SideNavItem
import com.konyaco.fluent.component.Text
import com.konyaco.fluent.icons.Icons
import com.konyaco.fluent.icons.regular.DeveloperBoard
import dev.faderainbow.yuanshen.config.sidenav.SideNavItemList
import dev.faderainbow.yuanshen.config.sidenav.SideNavRoute
import dev.faderainbow.yuanshen.ui.view.helper.viewmodel.NekoViewModel
import dev.faderainbow.yuanshen.utils.DrawNeko

/**
 *@author FadeRainbow
 *@date 2023/9/5
 *@time 19:11
 */
@Composable
 fun FrameViewBuilder(viewModel: ClientViewModel,nekoViewModel:NekoViewModel, content:@Composable () ->Unit) {
    Mica(
        modifier = Modifier.fillMaxSize()
    ) {//填充背景
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            SideNav(
                modifier = Modifier.fillMaxHeight(),
                expanded = viewModel.expanded,
                onExpandStateChange = {
                    viewModel.expanded = it
                },
                title = { Text("侧导栏") },
                footer = {
                    SideNavItem(
                        selected = viewModel.screen == SideNavRoute.Screen.TEST,
                        onClick = { viewModel.screen = SideNavRoute.Screen.TEST },
                        icon = { Icon(Icons.Default.DeveloperBoard, contentDescription = null) }
                    ) {
                        Text(text = "Test")
                        //实验
                    }
                }
            ) {
                /**
                 * this in config
                 */
                SideNavItemList(viewModel)

            }
            Layer(//另一层
                modifier = Modifier.weight(1f).fillMaxHeight().padding(top = 10.dp, end = 5.dp),
                outsideBorder = true,
                shape = RoundedCornerShape(8.dp),
                cornerRadius = 8.dp
            ) {
                content()
            }
        }
        DrawNeko(nekoViewModel, viewModel)
    }
}
