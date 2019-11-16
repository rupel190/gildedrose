package com.gildedrose

import com.gildedrose.updaters.ItemUpdaterFactory

class GildedRose(var items: Array<Item>) {

    fun update() {
        for (item in items) {
            ItemUpdaterFactory.retrieveUpdater(item).update(item)
        }
    }
}
