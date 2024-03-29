package com.gildedrose

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GildedRoseTest {

    //Not a prototype pattern but did the job regardless
    fun testItems(): Array<Item> {
        return arrayOf(
                Item("Amazin Prome", 5, 30),
                Item("Sulfuras, Hand of Ragnaros", 3, 80),
                Item("Aged Brie", 40, 12),
                Item("Backstage passes to a TAFKAL80ETC concert", 11, 20),
                Item("Conjured conjurer conjuring conjurables", 10, 10)
        )
    }

    //region Combine these?
    @Test
    fun `quality is reduced daily`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        //Act
        app.update()
        app.update()
        //Assert
        assertEquals(28, items.first().quality)
    }

    @Test
    fun `sellIn is reduced daily`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        //Act
        app.update()
        app.update()
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
            app.update()
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
            app.update()
        }
        //Assert
        items.forEach { println("quality: " + it.quality); assertTrue(it.quality >= 0) }
    }

    @Test
    fun `quality never exceeds 50`() {
        //Arrange
        val items = testItems().filter { !it.name.contains("sulfuras", true) }.toTypedArray()
        val app = GildedRose(items)
        //Act
        for (i in 1..50) {
            app.update()
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
            app.update()
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
            app.update()
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
            app.update()
        }
        //Assert
        assertTrue(sulfuras?.quality!! >= 4)
    }

    @Test
    fun `item - sulfuras quality is 80`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        val sulfuras = items.find { it.name.contains("sulfuras", true) }
        //Act
        for (i in 1..10) {
            app.update()
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
        app.update()
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
        app.update()
        app.update()
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
        app.update() //transistion 5->4
        //Assert
        assertEquals(4, backstagePass?.sellIn)
        assertEquals(23, backstagePass?.quality)
    }

    @Test
    fun `item - backstage passes quality drops to 0 after concert`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        val backstagePass = items.find { it.name.contains("backstage", true) }
        backstagePass?.sellIn = 1 //override for simpler test
        //Act
        app.update() //transition to day of concert
        app.update() //transition to day >after concert<
        //Assert
        assertEquals(0, backstagePass?.quality)
    }

    @Test
    fun `item - conjured degrades twice as fast`() {
        //Arrange
        val items = testItems()
        val app = GildedRose(items)
        val conjured = items.find { it.name.contains("conjured", true) }
        //Act
        app.update()
        app.update()
        //Assert
        assertEquals(6, conjured?.quality)
    }
    //endregion
}


