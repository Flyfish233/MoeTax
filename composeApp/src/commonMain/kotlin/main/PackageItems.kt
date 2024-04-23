package main

import kotlin.math.ceil

private const val TAX = 50 // http://hk.mofcom.gov.cn/article/ddfg/haiguan/201007/20100707007720.shtml

/**
 * Package items into packages.
 *
 * @param items List of purchased items.
 * @param exchangeRate The exchange rate between the item's currency and the destination currency - CNY -> JPY
 * @param packLimit The maximum number of items in a package - 030BUY: 5, Inagora: 15
 * @return List of packages.
 */
fun packageItems(
    items: List<Item>, exchangeRate: Double, packLimit: Int
): List<Package> {
    val sortedItems = items.map { item ->
        val totalCost = item.price + item.additionalFee
        val tax: Int = ceil((totalCost * item.taxRate.rate) * exchangeRate).toInt()
        if (tax > TAX) {
            throw Exception("Tax exceeds for item ${item.name}")
        }
        item to tax
    }.sortedByDescending { it.second }.toMutableList()

    val packages = mutableListOf<Package>()

    while (sortedItems.isNotEmpty()) {

        val currentPackage = mutableListOf<Item>()
        var currentTax = 0
        var totalValue = 0
        var i = 0

        while (i < sortedItems.size) {
            val (item, tax) = sortedItems[i]
            if (currentPackage.size < packLimit && currentTax + tax <= TAX) {
                currentPackage.add(item)
                currentTax += tax
                sortedItems.removeAt(i)
            } else {
                var foundCheaperItem = false
                for (j in i + 1 until sortedItems.size) {
                    // Start searching from the next item
                    val (cheaperItem, cheaperTax) = sortedItems[j]
                    if (currentPackage.size < packLimit && currentTax + cheaperTax <= TAX) {
                        currentPackage.add(cheaperItem)
                        currentTax += cheaperTax
                        sortedItems.removeAt(j)
                        foundCheaperItem = true
                        break
                    }
                }
                if (!foundCheaperItem) {
                    // If no cheaper item is found, move to the next item
                    i++
                }
            }
        }
        if (currentPackage.isNotEmpty()) {
            currentPackage.forEach { item ->
                totalValue += item.price + item.additionalFee
            }
            packages.add(Package(totalValue, currentTax, currentPackage))
        }
    }

    return packages
}