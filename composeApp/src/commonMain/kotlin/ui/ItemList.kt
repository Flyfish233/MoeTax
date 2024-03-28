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
    val preDefined = listOf(
    Item("新品未開封 ブシロード MyGO!!!!! 5thLIVE 迷うことに迷わない タオル", 1210, 1270, RATE.TWENTY),
    Item("「アスカさんはなびかない」オリジナルサウンドトラック", 2000, 80, RATE.TWENTY),
    Item("あざらしそふと 10th Anniversary Album with Duca", 1700, 300, RATE.TWENTY),
    Item("キリン 推しの子 クリアファイル 全 4 種 計 8 枚 A4 KIRIN 午後の紅茶", 599, 300, RATE.THIRTEEN),
    Item(
    "ショッパー 紙袋 購入特典 ハミクリ ハミダシクリエイティブ コラボカフェ まどそふと 常磐華乃 和泉妃愛 錦あすみ 鎌倉詩桜",
    980,
    450,
    RATE.TWENTY
    ),
    Item("僕の心のヤバイやつ　クリアファイル", 500, 300, RATE.TWENTY),
    Item(
    "予約特典 特製イラストカード 3 点セット　アスカさんはなびかない　ガルドマ　アマナツ",
    300,
    150,
    RATE.TWENTY
    ),
    Item("セレクトオブリージュ　まどそふと　C103", 2222, 750, RATE.TWENTY),
    Item("僕の心のヤバイやつ ショルダーバッグ カバン 山田杏奈 市川京太郎", 1499, 350, RATE.TWENTY),
    Item("アニサマ 2023 AXEL の MyGO 千早愛音のうちわ", 700, 300, RATE.TWENTY),
    Item("リコリスリコイル タオル", 900, 300, RATE.TWENTY),
    // Trigger exception:
    // Item("「BanG Dream! It's MyGO!!!!!」Blu-ray 上卷&下卷", 19800, 0, RATE.TWENTY),
    )
    items = preDefined
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