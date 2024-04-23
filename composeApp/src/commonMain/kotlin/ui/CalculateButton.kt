package ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import main.Item
import main.Package
import main.packageItems

@Stable
@Composable
fun CalculateButton(
    items: List<Item>, exchangeRate: Double, packageLimit: Int, onPackageSummaryChanged: (List<Package>) -> Unit
) {
    Button(onClick = {
        if (items.isEmpty() || items.last().name.isBlank()) {
            return@Button
        }
        val newPackageSummary = packageItems(items, exchangeRate, packageLimit)
        onPackageSummaryChanged(newPackageSummary)
    }) {
        Text("决定好了")
    }
}