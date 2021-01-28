package alisa.hash.test


import alisa.hash.HashTable
import kotlin.test.assertEquals
import kotlin.test.assertNull


fun main() {
    val testHashtable = HashTable(7)
    testHashtable[0xdead00] =  15
    testHashtable.add(0xdead01, 14)

    assertEquals(testHashtable[0xdead00], 15)
    assertEquals(testHashtable[0xdead01], 14)

    testHashtable[0xdead00] = 42
    assertEquals(testHashtable[0xdead00], 42)

    testHashtable.remove(0xdead01)
    assertNull(testHashtable.get(0xdead01))
}