package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

private const val EXCHANGE_RATE = 0.0487
private const val PACKAGE_LIMIT = 5

@OptIn(ExperimentalMaterial3Api::class)
@Stable
@Composable
@Preview
fun App() {
    var exchangeRate by remember { mutableStateOf(EXCHANGE_RATE) }
    var packageLimit by remember { mutableStateOf(PACKAGE_LIMIT) }

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.TopCenter)) {
            TopAppBar(title = { Text("MoeTax 包裹计算器") })

            Text(
                "填写当前汇率和包裹信息来合单打包: 每个包裹关税不超过 50 元。", modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text("汇率: 1 日元 = ")
                        TextField(modifier = Modifier.width(140.dp).height(55.dp),
                            singleLine = true,
                            suffix = { Text("人民币") },
                            value = "$exchangeRate",
                            onValueChange = {
                                exchangeRate = if (it.matches(Regex("""\d+(\.\d+)?"""))) it.toDouble()
                                else 0.0
                            })
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("最多含 ")
                        TextField(modifier = Modifier.width(70.dp).height(55.dp),
                            singleLine = true,
                            suffix = { Text("件") },
                            value = "$packageLimit",
                            onValueChange = {
                                packageLimit = if (it.matches(Regex("""\d+"""))) it.toInt()
                                else 5
                            })
                    }
                }
                item {
                    ItemList(
                        exchangeRate = exchangeRate, packageLimit = packageLimit
                    )
                }
            }
        }
    }
}