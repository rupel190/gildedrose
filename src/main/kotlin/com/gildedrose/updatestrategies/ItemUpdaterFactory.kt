package com.gildedrose.updatestrategies

import com.gildedrose.Item

class ItemUpdaterFactory (item: Item){

    companion object {
        //todo: change return type
        fun retrieveUpdater (item: Item) : ItemUpdater? {
            return when {
                //todo: change to correct updater
                item.name.contains("Aged Brie", true) -> null
                item.name.contains("Sulfuras", true) -> null
                item.name.contains("Backstage passes", true) -> null
                else -> DefaultItemUpdater()
            }
        }
    }
}