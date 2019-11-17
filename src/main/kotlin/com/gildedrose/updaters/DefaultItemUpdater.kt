package com.gildedrose.updaters

import com.gildedrose.Item


private const val QUALITYTHRESHOLD = 0
private const val SELLINTHRESHOLD = 0

class DefaultItemUpdater : ItemUpdater {
    override fun update(item: Item) {
        item.sellIn--
        if (item.quality <= QUALITYTHRESHOLD) {
            return
        }
        item.quality--
        if(item.quality > QUALITYTHRESHOLD && item.sellIn < SELLINTHRESHOLD) {
            item.quality--
        }
    }
}
