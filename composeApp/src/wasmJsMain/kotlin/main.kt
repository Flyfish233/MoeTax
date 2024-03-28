import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.window.CanvasBasedWindow
import ui.App
import ui.AppTheme

// JavaScript name (main) generated for this declaration clashes with another declaration: package-fragment main
// IDK; Just suppress this warning
@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow(canvasElementId = "ComposeTarget") {
        var typography by remember { mutableStateOf<Typography?>(null) }
        LaunchedEffect(Unit) {
            val font = loadFont()
            typography = Typography(
                displayLarge = TextStyle(fontFamily = font),
                displayMedium = TextStyle(fontFamily = font),
                displaySmall = TextStyle(fontFamily = font),
                headlineLarge = TextStyle(fontFamily = font),
                headlineMedium = TextStyle(fontFamily = font),
                headlineSmall = TextStyle(fontFamily = font),
                titleLarge = TextStyle(fontFamily = font),
                titleMedium = TextStyle(fontFamily = font),
                titleSmall = TextStyle(fontFamily = font),
                bodyLarge = TextStyle(fontFamily = font),
                bodyMedium = TextStyle(fontFamily = font),
                bodySmall = TextStyle(fontFamily = font),
                labelLarge = TextStyle(fontFamily = font),
                labelMedium = TextStyle(fontFamily = font),
                labelSmall = TextStyle(fontFamily = font),
            )
        }
        AppTheme(typography = typography ?: MaterialTheme.typography) {
            App()
        }
    }
}