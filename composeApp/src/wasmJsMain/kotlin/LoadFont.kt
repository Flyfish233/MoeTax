import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.readResourceBytes

@OptIn(InternalResourceApi::class)
suspend fun loadFont(): FontFamily {
    val regular = readResourceBytes("fonts/NotoSansCJK_SC_JP_Trimmed.ttf")
    return FontFamily(
        Font(identity = "CJKRegular", data = regular, weight = FontWeight.Normal)
    )
}