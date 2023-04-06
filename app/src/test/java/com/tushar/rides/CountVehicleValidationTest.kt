package com.tushar.rides

import com.tushar.rides.ui.main.MainFragment
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CountVehicleValidationTest {
    lateinit var mainFragment: MainFragment

    @Before
    fun setup() {
        mainFragment = MainFragment()
    }
    @Test
    fun `valid count returns true`() {
        assertTrue(mainFragment.isValidCount(1))
        assertTrue(mainFragment.isValidCount(50))
        assertTrue(mainFragment.isValidCount(100))
    }

    @Test
    fun `null count returns false`() {
        assertFalse(mainFragment.isValidCount(null))
    }

    @Test
    fun `count less than 1 returns false`() {
        assertFalse(mainFragment.isValidCount(0))
        assertFalse(mainFragment.isValidCount(-10))
    }

    @Test
    fun `count greater than 100 returns false`() {
        assertFalse(mainFragment.isValidCount(101))
        assertFalse(mainFragment.isValidCount(1000))
    }
}