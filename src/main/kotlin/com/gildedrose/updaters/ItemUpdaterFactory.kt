package com.gildedrose.updaters

import com.gildedrose.Item

class ItemUpdaterFactory private constructor() {

    companion object {
        fun retrieveUpdater (item: Item) : ItemUpdater {
            return when {
                item.name.contains("Aged Brie", true) -> BrieItemUpdater()
                item.name.contains("Sulfuras", true) -> SulfurasItemUpdater()
                item.name.contains("Backstage passes", true) -> BackstagepassItemUpdater()
                item.name.contains("conjured", true) -> ConjuredItemUpdater()
                else -> DefaultItemUpdater()
            }
        }
    }
}
