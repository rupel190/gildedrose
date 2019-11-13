package com.gildedrose.updatestrategies

import com.gildedrose.Item

class DefaultItemUpdater : ItemUpdater {
    override fun update(item : Item) {
        item.sellIn--
        if(item.quality > 0) {
            item.quality--
        }
        if(item.sellIn < 0) {
            item.quality--
        }
    }
}