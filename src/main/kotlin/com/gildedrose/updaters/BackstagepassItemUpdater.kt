package com.gildedrose.updaters

import com.gildedrose.Item


private const val MAXQUALITY = 50

private const val WORTHLESSQUALITY = 0
private const val SMALLQUALITYINCREMENT = 1
private const val MEDIUMQUALITYINCREMENT = 2
private const val LARGEQUALITYINCREMENT = 3

private const val THRESHOLDSELLINOVER = 0
private const val THRESHOLDSELLINSHORTLY = 5
private const val THRESHOLDSELLINSOON = 10

class BackstagepassItemUpdater : ItemUpdater {

    override fun update(item: Item) {
        //order important
        item.sellIn--
            when {
                item.sellIn <= THRESHOLDSELLINOVER -> item.quality = WORTHLESSQUALITY
                item.sellIn < THRESHOLDSELLINSHORTLY -> item.quality += LARGEQUALITYINCREMENT
                item.sellIn < THRESHOLDSELLINSOON -> item.quality += MEDIUMQUALITYINCREMENT
                else -> item.quality += SMALLQUALITYINCREMENT
            }
        if(item.quality > MAXQUALITY) {
            item.quality = MAXQUALITY
        }
    }
}
