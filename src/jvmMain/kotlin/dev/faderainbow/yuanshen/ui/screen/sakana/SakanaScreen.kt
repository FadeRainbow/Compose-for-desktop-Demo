package dev.faderainbow.yuanshen.ui.screen.sakana

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.konyaco.fluent.animation.FluentDuration
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import javax.sound.sampled.AudioSystem


/**
 *@author FadeRainbow
 *@date 2023/9/2
 *@time 21:02
 */
@Composable
fun SakanaScreen(sakanaViewModel: SakanaViewModel){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           Row(
               modifier = Modifier.fillMaxWidth()
           ) {
               ImageView("Sakana","takina.png","sakana.wav")
               ImageView("Chinanago~","chisato.png","chinanago.wav")
           }
        }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ImageView(text:String,imagePath:String,songPath:String){
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(visible) {
        if (visible){
            delay(2500)
            visible=false
        }
    }
    Column(
    ) {
        AnimatedVisibility(
            modifier =Modifier.padding(start = 160.dp/2) ,
            visible=visible,
            enter = expandVertically(animationSpec = tween(FluentDuration.LongDuration),
                expandFrom = Alignment.Top),
            exit = shrinkOut(animationSpec = tween(FluentDuration.LongDuration))
        ){
            Text(
                text=text
            )
        }
        ImageButton(path=imagePath){
            visible=true
            playAudio(songPath)
        }
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
private inline fun ImageButton(path:String, imageSize:Int=220, crossinline onClick:()->Unit){
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit){
        delay(500)
        visible=true
    }
    IconButton(onClick = {
        onClick()
    }
    ){
        AnimatedVisibility(
            visible = visible,
            enter = expandVertically(animationSpec = tween(FluentDuration.LongDuration),
                expandFrom = Alignment.Top)+ scaleIn(animationSpec = tween(FluentDuration.LongDuration))
        ){
            Image(
                painter = painterResource(path),
                contentDescription = null,
                modifier = Modifier.size(imageSize.dp)
            )
        }
    }
}
@OptIn(DelicateCoroutinesApi::class)
private fun playAudio(path:String) {
    GlobalScope.launch {
        val clip = AudioSystem.getClip()
        val audioStream = AudioSystem.getAudioInputStream(File("F:\\BaiduNetdiskDownload\\DEV\\Compose Desktop\\FluentUi\\demo\\src\\jvmMain\\resources\\$path"))
        clip.open(audioStream)
        clip.start()
        delay(clip.microsecondLength / 1000)
        clip.close()
    }
}



