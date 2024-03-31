import androidx.compose.ui.window.ComposeUIViewController
import ui.App
import ui.AppTheme

fun MainViewController() = ComposeUIViewController {
    AppTheme {
        App()
    }
}