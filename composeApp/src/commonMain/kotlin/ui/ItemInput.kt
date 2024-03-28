package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import main.Item

@Stable
@Composable
fun ItemInput(item: Item, onItemChanged: (Item) -> Unit, onDelete: () -> Unit) {
    ExpandableCard(title = "商品 ${item.name}") {
        Column(Modifier.padding(16.dp)) {
            TextField(value = item.name,
                onValueChange = { onItemChanged(item.copy(name = it)) },
                label = { Text("商品名称") })
            BoxWithConstraints {
                if (maxWidth < 600.dp) {
                    Column {
                        TextField(value = "${item.price}",
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { onItemChanged(item.copy(price = it.toIntOrNull() ?: 0)) },
                            suffix = { Text("日元") },
                            label = { Text("价格") })
                        TextField(value = "${item.additionalFee}",
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { onItemChanged(item.copy(additionalFee = it.toIntOrNull() ?: 0)) },
                            suffix = { Text("日元") },
                            label = { Text("附加费用") })
                        Row {
                            Box(modifier = Modifier.weight(1f)) {
                                TaxRateMenu(item, onItemChanged)
                            }
                            IconButton(
                                modifier = Modifier.padding(8.dp), onClick = onDelete
                            ) {
                                Icon(Icons.Filled.Delete, contentDescription = "删除")
                            }
                        }
                    }
                } else {
                    Row {
                        TextField(value = "${item.price}",
                            modifier = Modifier.width(200.dp),
                            onValueChange = { onItemChanged(item.copy(price = it.toIntOrNull() ?: 0)) },
                            suffix = { Text("日元") },
                            label = { Text("价格") })
                        TextField(value = "${item.additionalFee}",
                            modifier = Modifier.width(200.dp),
                            onValueChange = { onItemChanged(item.copy(additionalFee = it.toIntOrNull() ?: 0)) },
                            suffix = { Text("日元") },
                            label = { Text("附加费用") })
                        TaxRateMenu(item, onItemChanged)
                        IconButton(
                            modifier = Modifier.padding(8.dp), onClick = onDelete
                        ) {
                            Icon(Icons.Filled.Delete, contentDescription = "删除")
                        }
                    }
                }
            }
        }
    }
}