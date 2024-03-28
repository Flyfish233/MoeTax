package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import main.Item
import main.Package
import main.RATE

@Stable
@Composable
fun ItemList(
    exchangeRate: Double
) {
    var items by remember { mutableStateOf(listOf<Item>()) }
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var packages by remember { mutableStateOf(listOf<Package>()) }

    AnimatedVisibility(showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Card(
                modifier = Modifier.size(150.dp),
            ) {
                Column(
                    Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(errorMessage)
                    Spacer(modifier = Modifier.height(32.dp))
                    Button({ showDialog = false }) {
                        Text("确定")
                    }
                }
            }
        }
    }

    /**
     * @suppress Test only
    if (items.isEmpty()) {
    items = testItems
    }
     **/

    if (items.isEmpty()) {
        items = items + Item("", 0, 0, RATE.TWENTY)
    }

    items.forEachIndexed { index, item ->
        ItemInput(item, onItemChanged = { updatedItem ->
            val newItems = items.toMutableList()
            newItems[index] = updatedItem
            items = newItems
        }, onDelete = {
            val newItems = items.toMutableList()
            newItems.removeAt(index)
            items = newItems
        })
    }

    Row(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            if (items.isNotEmpty() && items.last().name.isBlank()) {
                errorMessage = "请填写完整信息"
                showDialog = true
            } else {
                items = items + Item("", 0, 0, RATE.TWENTY)
            }
        }) {
            Text("添加物品")
        }
        Spacer(modifier = Modifier.width(16.dp))
        CalculateButton(items, exchangeRate) { newPackages ->
            packages = newPackages
        }
    }

    if (packages.isNotEmpty()) {
        DisplayPackages(packages)
    }
}