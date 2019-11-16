package com.gildedrose.updaters

import com.gildedrose.Item

class ConjuredItemUpdater : ItemUpdater {
    override fun update(item: Item) {
        item.sellIn--
        if(item.quality > 1) {
            item.quality-=2
        }
    }
}
