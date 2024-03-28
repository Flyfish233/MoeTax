package ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.materialkolor.DynamicMaterialTheme
import com.materialkolor.PaletteStyle

@Stable
@Composable
fun AppTheme(
    seedColor: Color = Color(0xFF6200EE),
    paletteStyle: PaletteStyle = PaletteStyle.TonalSpot,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    typography: Typography = MaterialTheme.typography, // For wasmJS target with custom fonts
    content: @Composable () -> Unit,
) {
    MaterialTheme(content = {
        DynamicMaterialTheme(seedColor = seedColor,
            useDarkTheme = useDarkTheme,
            style = paletteStyle,
            animate = true,
            typography = typography,
            content = {
                Surface(content = content)
            })
    })
}