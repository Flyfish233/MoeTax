package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CurrencyExchange
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import main.Package

@Stable
@Composable
fun DisplayPackages(packages: List<Package>) {
    packages.forEachIndexed { index, pack ->
        ExpandableCard(title = "包裹 ${index + 1}") {
            Column(Modifier.padding(16.dp)) {
                Row {
                    Icon(Icons.Rounded.MonetizationOn, contentDescription = "总价")
                    Text("总价: ${pack.totalValue} 日元")
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(Icons.Rounded.CurrencyExchange, contentDescription = "关税")
                    Text("关税: ${pack.totalTax} 人民币")
                }
                pack.items.forEachIndexed { index, item ->
                    Row {
                        Text("商品 ${index + 1}: ${item.name}")
                    }
                }
            }
        }
    }
}