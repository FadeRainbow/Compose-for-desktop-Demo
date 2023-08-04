package utils
import java.awt.Color
import java.util.*
import java.util.regex.Pattern
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min
/**
 *@author FadeRainbow
 *@date 2023/7/30
 *@time 11:11
 */
object ColorUtils {
    @JvmStatic
    fun skyRainbow(var2: Int, bright: Float, st: Float, speed: Double): Color {
        var v1 = ceil(System.currentTimeMillis() / speed + var2 * 109L) / 5
        return Color.getHSBColor(if ((360.0.also { v1 %= it } / 360.0) <0.5) { -(v1 / 360.0).toFloat() } else { (v1 / 360.0).toFloat() }, st, bright)
    }
    @JvmStatic
    fun getRainbowOpaque(seconds: Int, saturation: Float, brightness: Float, index: Int):
            Int { val hue = (System.currentTimeMillis() + index) % (seconds * 1000) / (seconds * 1000).toFloat()
        return Color.HSBtoRGB(hue, saturation, brightness)
    }

    @JvmStatic
    fun slowlyRainbow(time: Long, count: Int, qd: Float, sq: Float): Color {
        val color = Color(Color.HSBtoRGB((time.toFloat() + count * -3000000f) / 2 / 1.0E9f, qd, sq))
        return Color(color.red / 255.0f * 1, color.green / 255.0f * 1, color.blue / 255.0f * 1, color.alpha / 255.0f)
    }
    val allowedCharactersArray = charArrayOf('/', '\n', '\r', '\t', '\u0000', '', '`', '?', '*', '\\', '<', '>', '|', '\"', ':')

    fun isAllowedCharacter(character: Char): Boolean {
        return character.toInt() != 167 && character.toInt() >= 32 && character.toInt() != 127
    }


    fun hsbTransition(from: Float, to: Float, angle: Int, s: Float = 1f, b: Float = 1f): Color {
        return Color.getHSBColor(
            if (angle < 180) from + (to - from) * (angle / 180f)
            else from + (to - from) * (-(angle - 360) / 180f), s, b)
    }


    @JvmStatic
    fun colorCode(code: String, alpha: Int = 255): Color {
        when (code.toLowerCase()) {
            "0" -> {
                return Color(0, 0, 0, alpha)
            }
            "1" -> {
                return Color(0, 0, 170, alpha)
            }
            "2" -> {
                return Color(0, 170, 0, alpha)
            }
            "3" -> {
                return Color(0, 170, 170, alpha)
            }
            "4" -> {
                return Color(170, 0, 0, alpha)
            }
            "5" -> {
                return Color(170, 0, 170, alpha)
            }
            "6" -> {
                return Color(255, 170, 0, alpha)
            }
            "7" -> {
                return Color(170, 170, 170, alpha)
            }
            "8" -> {
                return Color(85, 85, 85, alpha)
            }
            "9" -> {
                return Color(85, 85, 255, alpha)
            }
            "a" -> {
                return Color(85, 255, 85, alpha)
            }
            "b" -> {
                return Color(85, 255, 255, alpha)
            }
            "c" -> {
                return Color(255, 85, 85, alpha)
            }
            "d" -> {
                return Color(255, 85, 255, alpha)
            }
            "e" -> {
                return Color(255, 255, 85, alpha)
            }
            else -> {
                return Color(255, 255, 255, alpha)
            }
        }
    }

    @JvmField
    val hexColors = IntArray(16)

    init {
        repeat(16) { i ->
            val baseColor = (i shr 3 and 1) * 85

            val red = (i shr 2 and 1) * 170 + baseColor + if (i == 6) 85 else 0
            val green = (i shr 1 and 1) * 170 + baseColor
            val blue = (i and 1) * 170 + baseColor

            hexColors[i] = red and 255 shl 16 or (green and 255 shl 8) or (blue and 255)
        }
    }
    @JvmStatic
    fun healthColor(hp: Float, maxHP: Float, alpha: Int = 255): Color {
        val pct = ((hp / maxHP) * 255F).toInt()
        return Color(max(min(255 - pct, 255), 0), max(min(pct, 255), 0), 0, alpha)
    }
    fun darker(color: Color, percentage: Float): Color {
        return Color((color.red * percentage).toInt(), (color.green * percentage).toInt(), (color.blue * percentage).toInt(), (color.alpha * percentage).toInt())
    }    @JvmStatic
    fun rainbowW(offset: Long): Color {
        val currentColor = Color(Color.HSBtoRGB((System.nanoTime() + offset) / 10000000000F % 1, 0.6F, 1F))
        return Color(0F, currentColor.red.toFloat() / 255.0F * 1.0F, currentColor.blue.toFloat() / 255.0F * 1.0F, currentColor.alpha.toFloat() / 255.0F)
    }
    @JvmStatic
    fun redRainbow(offset: Long): Color {
        val currentColor = Color(Color.HSBtoRGB((System.nanoTime() + offset) / 10000000000F % 1, 0.5F, 1F))
        return Color(currentColor.red.toFloat() / 255.0F * 1.0F, 0F, 0F, currentColor.alpha.toFloat() / 255.0F)
    }
    @JvmStatic
    fun greenRainbow(offset: Long): Color {
        val currentColor = Color(Color.HSBtoRGB((System.nanoTime() + offset) / 10000000000F % 1, 0.5F, 1F))
        return Color(0F, currentColor.green / 255.0F * 1.0F, 0F, currentColor.alpha.toFloat() / 255.0F)
    }
    @JvmStatic
    fun blueRainbow(offset: Long): Color {
        val currentColor = Color(Color.HSBtoRGB((System.nanoTime() + offset) / 10000000000F % 1, 0.5F, 1F))
        return Color(0F, 0F, currentColor.blue.toFloat() / 255.0F * 1.0F, currentColor.alpha.toFloat() / 255.0F)
    }
    @JvmStatic
    fun rainbow3(offset: Long, rainbowSpeed: Float, rainbowBright: Float): Color {
        val currentColor = Color(Color.HSBtoRGB((System.nanoTime() + offset) / 10000000000F % 1, rainbowSpeed, rainbowBright))
        return Color(currentColor.red.toFloat() / 255.0f * 1.0f, currentColor.green.toFloat() / 255.0f * 1.0f, currentColor.blue.toFloat() / 255.0f * 1.0f, currentColor.alpha.toFloat() / 255.0f)
    }


    private val startTime=System.currentTimeMillis()
    fun hslRainbow(index: Int,lowest: Float=0.41f,bigest: Float=0.58f,indexOffset: Int=300,timeSplit: Int=1500):Color{
        return Color.getHSBColor((abs(((((System.currentTimeMillis()-startTime).toInt()+index*indexOffset)/timeSplit.toFloat())%2)-1) *(bigest-lowest))+lowest,0.7f,1f)
    }
    @JvmStatic
    fun fade(color: Color, index: Int, count: Int): Color {
        val hsb = FloatArray(3)
        Color.RGBtoHSB(color.red, color.green, color.blue, hsb)
        var brightness =
            abs(((System.currentTimeMillis() % 2000L).toFloat() / 1000.0f + index.toFloat() / count.toFloat() * 2.0f) % 2.0f - 1.0f)
        brightness = 0.5f + 0.5f * brightness
        hsb[2] = brightness % 2.0f
        return Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]))
    }

    @JvmStatic
    fun rainbow(): Color {
        val currentColor = Color(Color.HSBtoRGB((System.nanoTime() + 400000L) / 10000000000F % 1, 1F, 1F))
        return Color(currentColor.red / 255F * 1F, currentColor.green / 255f * 1F, currentColor.blue / 255F * 1F, currentColor.alpha / 255F)
    }

    @JvmStatic
    fun rainbow(offset: Long): Color {
        val currentColor = Color(Color.HSBtoRGB((System.nanoTime() + offset) / 10000000000F % 1, 1F, 1F))
        return Color(currentColor.red / 255F * 1F, currentColor.green / 255F * 1F, currentColor.blue / 255F * 1F,
            currentColor.alpha / 255F)
    }

    @JvmStatic
    fun rainbow(alpha: Float) = rainbow(400000L, alpha)

    @JvmStatic
    fun rainbow(alpha: Int) = rainbow(400000L, alpha / 255)

    @JvmStatic
    fun rainbow(offset: Long, alpha: Int) = rainbow(offset, alpha.toFloat() / 255)


    @JvmStatic
    fun originalrainbow(offset: Long): Color {
        val currentColor = Color(Color.HSBtoRGB((System.nanoTime() + offset) / 10000000000F % 1, 1.0F, 1.0F))
        return Color(currentColor.red / 255F * 1F, currentColor.green / 255F * 1F, currentColor.blue / 255F * 1F,
            currentColor.alpha / 255F)
    }

    @JvmStatic
    fun LiquidSlowly(time: Long, count: Int, qd: Float, sq: Float): Color? {
        val color = Color(Color.HSBtoRGB((time.toFloat() + count * -3000000f) / 2 / 1.0E9f, qd, sq))
        return Color(color.red / 255.0f * 1, color.green / 255.0f * 1, color.blue / 255.0f * 1, color.alpha / 255.0f)
    }
    @JvmStatic
    fun rainbow(offset: Long, alpha: Float): Color {
        val currentColor = Color(Color.HSBtoRGB((System.nanoTime() + offset) / 10000000000F % 1, 1F, 1F))
        return Color(currentColor.red / 255F * 1F, currentColor.green / 255f * 1F, currentColor.blue / 255F * 1F, alpha)
    }
    @JvmStatic
    fun TwoRainbow(offset: Long,alpha: Float): Color {
        var currentColor = Color(Color.HSBtoRGB((System.nanoTime() + offset) / 8.9999999E10F % 1, 0.75F, 0.8F));
        return Color(currentColor.getRed() / 255.0F * 1.0F, currentColor.getGreen() / 255.0F * 1.0F, currentColor.getBlue() / 255.0F * 1.0F, alpha);
    }
}