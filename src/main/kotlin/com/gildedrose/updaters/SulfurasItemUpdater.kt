package com.gildedrose.updaters

import com.gildedrose.Item


private const val SPECIALQUALITY = 80

class SulfurasItemUpdater : ItemUpdater {
    override fun update(item: Item) {
        //make sure quality is 80
        item.quality = SPECIALQUALITY
    }
}
