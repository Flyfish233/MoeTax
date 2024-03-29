package ui

import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import main.Item
import main.RATE

private val taxString = arrayOf("20%", "13%")

@OptIn(ExperimentalMaterial3Api::class)
@Stable
@Composable
fun TaxRateMenu(
    item: Item, onItemChanged: (Item) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(taxString[0]) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
        expanded = !expanded
    }) {
        TextField(
            value = selectedText,
            label = { Text("税率") },
            onValueChange = {
                onItemChanged(item.copy(taxRate = if (it == "20%") RATE.TWENTY else RATE.THIRTEEN))
            },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().width(120.dp)
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            taxString.forEach { item ->
                DropdownMenuItem(text = { Text(text = item) }, onClick = {
                    selectedText = item
                    expanded = false
                })
            }
        }
    }
}