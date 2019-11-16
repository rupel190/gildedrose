package com.gildedrose.updaters

import com.gildedrose.Item


private const val MAXQUALITY = 50

class BrieItemUpdater : ItemUpdater {

    override fun update(item: Item) {
        item.sellIn--
        if (item.quality < MAXQUALITY) {
            item.quality++
        }
    }
}
