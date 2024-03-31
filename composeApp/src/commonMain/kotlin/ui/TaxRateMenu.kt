package ui

import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import main.Item
import main.RATE

private val taxRateOptions = arrayOf(RATE.THIRTEEN, RATE.TWENTY)
private val taxRateStrings = arrayOf("13%", "20%")

@OptIn(ExperimentalMaterial3Api::class)
@Stable
@Composable
fun TaxRateMenu(
    item: Item, onItemChanged: (Item) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(item.taxRate) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
        expanded = !expanded
    }) {
        TextField(
            value = taxRateStrings[taxRateOptions.indexOf(selectedOption)],
            label = { Text("税率") },
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().width(120.dp)
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            taxRateOptions.forEachIndexed { index, option ->
                DropdownMenuItem(text = { Text(text = taxRateStrings[index]) }, onClick = {
                    selectedOption = option
                    expanded = false
                    onItemChanged(item.copy(taxRate = option))
                })
            }
        }
    }
}