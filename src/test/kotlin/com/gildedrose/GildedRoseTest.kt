package com.gildedrose

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GildedRoseTest {

    fun testItems(): Array<Item> {
        return arrayOf(
                Item("Amazin Prome", 5, 30),
                Item("Sulfuras, Hand of Ragnaros", 3, 4),
                Item("Aged Brie", 40, 12),
                Item("Backstage passes to a TAFKAL80ETC concert", 11, 20)
        )
    }

    //region Combine these?
    @Test
    fun `quality is reduced daily`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        //Act
        app.updateQuality()
        app.updateQuality()
        //Assert
        assertEquals(28, items.first().quality)
    }

    @Test
    fun `sellIn is reduced daily`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        //Act
        app.updateQuality()
        app.updateQuality()
        //Assert
        assertEquals(3, items.first().sellIn)
    }
    //endregion

    @Test
    fun `sell date passed - double quality degradation`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        val amazin = items.find { it.name.contains("amazin", true) }
        //Act
        for (i in 1..10) {
            app.updateQuality()
        }
        //Assert
        assertEquals(15, amazin?.quality)

    }

    @Test
    fun `quality never negative`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        //Act
        for (i in 1..50) {
            app.updateQuality()
        }
        //Assert
        items.forEach { println("quality: " + it.quality); assertTrue(it.quality >= 0) }
    }

    @Test
    fun `quality never exceeds 50`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        //Act
        for (i in 1..50) {
            app.updateQuality()
        }
        //Assert
        items.forEach { assertTrue(it.quality <= 50) }
    }

    //region Specific Items
    @Test
    fun `item - aged brie quality increases`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        val brie = items.find { it.name.contains("brie", true) }
        //Act
        for (i in 1..5) {
            app.updateQuality()
        }
        //Assert
        assertEquals(17, brie?.quality)
    }

    @Test
    fun `item - sulfuras doesn't have to be sold`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        val sulfuras = items.find { it.name.contains("sulfuras", true) }
        //Act
        for (i in 1..50) {
            app.updateQuality()
        }
        //Assert
        assertTrue(sulfuras?.sellIn!! > 0)
    }

    @Test
    fun `item - sulfuras doesn't degrade`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        val sulfuras = items.find { it.name.contains("sulfuras", true) }
        //Act
        for (i in 1..50) {
            app.updateQuality()
        }
        //Assert
        assertTrue(sulfuras?.quality!! >= 4)
    }

    @Test
    fun `item - sulfuras doesn't exceed 80`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        val sulfuras = items.find { it.name.contains("sulfuras", true) }
        //Act
        for (i in 1..100) {
            app.updateQuality()
        }
        //Assert
        assertEquals(80, sulfuras?.quality)
    }

    @Test
    fun `item - backstage passes quality increases by 1 when more than 10 days`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        val backstagePass = items.find { it.name.contains("backstage", true) }
        //Act
        app.updateQuality()
        //Assert
        assertEquals(10, backstagePass?.sellIn)  //11->10
        assertEquals(21, backstagePass?.quality) //20->21
    }

    @Test
    fun `item - backstage passes quality increases by 2 when 10 days or less`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        val backstagePass = items.find { it.name.contains("backstage", true) }
        //Act
        app.updateQuality()
        app.updateQuality()
        //Assert
        assertEquals(9, backstagePass?.sellIn)   //11->10 ; 10->9
        assertEquals(23, backstagePass?.quality) //20->21 ; 21->23
    }

    @Test
    fun `item - backstage passes quality increases by 3 when 5 days or less`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        val backstagePass = items.find { it.name.contains("backstage", true) }
        backstagePass?.sellIn = 5 //override for simpler test
        //Act
        app.updateQuality()
        //Assert
        assertEquals(4, backstagePass?.sellIn)   //5-> 4
        assertEquals(23, backstagePass?.quality) //20->23
    }

    @Test
    fun `item - backstage passes quality drops to 0 after concert`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        val backstagePass = items.find { it.name.contains("backstage", true) }
        backstagePass?.sellIn = 1 //override for simpler test
        //Act
        app.updateQuality()
        app.updateQuality()
        //Assert
        assertEquals(-1, backstagePass?.sellIn) //day of concert: 1->0 ; after concert: 0-> -1
        assertEquals(0, backstagePass?.quality) //20->0
    }
    //endregion
}


