import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.App
import ui.AppTheme

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Moe Tax") {
        AppTheme {
            App()
        }
    }
}