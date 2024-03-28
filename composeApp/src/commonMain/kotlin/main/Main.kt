package main

/**
 * This is not the main entry of MoeTax GUI.
 */
fun main() {
    val items = listOf(
        Item("新品未開封 ブシロード MyGO!!!!! 5thLIVE 迷うことに迷わない タオル", 1210, 1270, RATE.TWENTY),
        Item("「アスカさんはなびかない」オリジナルサウンドトラック", 2000, 80, RATE.TWENTY),
        Item("あざらしそふと 10th Anniversary Album with Duca", 1700, 300, RATE.TWENTY),
        Item("キリン 推しの子 クリアファイル 全 4 種 計 8 枚 A4 KIRIN 午後の紅茶", 599, 300, RATE.THIRTEEN),
        Item(
            "ショッパー 購入特典 ハミクリ ハミダシクリエイティブ コラボカフェ まどそふと 常磐華乃 和泉妃愛 錦あすみ 鎌倉詩桜",
            980,
            450,
            RATE.TWENTY
        ),
        Item("僕の心のヤバイやつ　クリアファイル", 500, 300, RATE.TWENTY),
        Item("予約特典 特製イラストカード 3 点セット　アスカさんはなびかない　ガルドマ　アマナツ", 300, 150, RATE.TWENTY),
        Item("セレクトオブリージュ　まどそふと　C103", 2222, 750, RATE.TWENTY),
        Item("僕の心のヤバイやつ ショルダーバッグ カバン 山田杏奈 市川京太郎", 1499, 350, RATE.TWENTY),
        Item("アニサマ 2023 AXEL の MyGO 千早愛音のうちわ", 700, 300, RATE.TWENTY),
        Item("リコリスリコイル タオル", 900, 300, RATE.TWENTY),
        // Trigger exception:
        // Item("「BanG Dream! It's MyGO!!!!!」Blu-ray 上卷&下卷", 19800, 0, RATE.TWENTY),
    )

    val packages = packageItems(items, 0.0497)

    packages.forEachIndexed { index, pack ->
        println("Package ${index + 1}:")
        pack.items.forEachIndexed { i, item ->
            println("Item ${i + 1}: ${item.name}, Price: ${item.price}, Additional Fee: ${item.additionalFee}")
        }
        println("Total Value: ${pack.totalValue} JPY, Total Tax: ${pack.totalTax} CNY")
    }
}