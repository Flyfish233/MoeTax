package main

data class Item(
    val name: String, // 物品名称
    val price: Int, // 物品金额
    val additionalFee: Int, // 附加费用
    val taxRate: RATE, // 税率
)