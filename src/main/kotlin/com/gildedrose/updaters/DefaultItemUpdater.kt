package com.gildedrose.updaters

import com.gildedrose.Item


private const val QUALITYTHRESHOLD = 0
private const val SELLINTHRESHOLD = 0

class DefaultItemUpdater : ItemUpdater {

    override fun update(item: Item) {
        item.sellIn--
        if (item.quality > QUALITYTHRESHOLD) {
            item.quality--
        }
        if(item.quality > QUALITYTHRESHOLD+1 && item.sellIn <= SELLINTHRESHOLD) {
            if(item.sellIn < SELLINTHRESHOLD) {
                item.quality--
            }
        }
    }
}
