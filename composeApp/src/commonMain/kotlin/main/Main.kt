package main

/**
 * This is not the entry of MoeTax GUI.
 */
fun main() {
    val packages = packageItems(testItems, 0.0497, 2)

    packages.forEachIndexed { index, pack ->
        println("Package ${index + 1}:")
        pack.items.forEachIndexed { i, item ->
            println("Item ${i + 1}: ${item.name}, Price: ${item.price}, Additional Fee: ${item.additionalFee}")
        }
        println("Total Value: ${pack.totalValue} JPY, Total Tax: ${pack.totalTax} CNY")
    }
}