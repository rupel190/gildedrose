package com.gildedrose

import com.gildedrose.updatestrategies.ItemUpdaterFactory

class GildedRose(var items: Array<Item>) {

    fun updateQualityNew() {
        for (item in items) {
            //choose correct updater for item
            val updater = ItemUpdaterFactory.retrieveUpdater(item)
            if (updater != null) {
                updater.update(item)
            } else {
                update()
            }
        }

    }

    fun update() {
        for (i in items.indices) {
            updateSulfuras(i)
            updateQuality(i)
            updateBackstage(i)
            reduceSellIn(i)


            if (items[i].sellIn < 0) {
                negativeSellIn(i)
            }
        }
    }

    private fun updateBackstage(i: Int) {
        if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (items[i].sellIn < 11) {
                updateQuality(i)
                updateQuality(i)
            }
            if (items[i].sellIn < 5) {
                updateQuality(i)

            }
        }
    }

    private fun negativeSellIn(i: Int) {
        if (!items[i].name.equals("Aged Brie")) {

            if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                updateQuality(i)
            } else {
                items[i].quality = 0
            }

        } else {
            updateQuality(i)
        }
    }

    private fun reduceSellIn(i: Int) {
        if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
            items[i].sellIn--
        }
    }


    private fun updateSulfuras(i: Int) {
        if (items[i].quality < 80 && items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
            items[i].quality++
        }
    }

    private fun updateQuality(i: Int) {
        if (items[i].quality in 1..49 && !items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
            if(items[i].name.equals("Aged Brie")) {
                items[i].quality++
            }
            items[i].quality--
        }
    }

}

