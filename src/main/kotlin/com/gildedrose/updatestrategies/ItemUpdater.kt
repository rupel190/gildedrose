package com.gildedrose.updatestrategies

import com.gildedrose.Item

interface ItemUpdater {
    fun update(item: Item)
}