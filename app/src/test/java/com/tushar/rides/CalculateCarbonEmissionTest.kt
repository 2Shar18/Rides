package com.tushar.rides

import org.junit.Assert;
import org.junit.Before
import org.junit.Test;

class CalculateCarbonEmissionTest {
    lateinit var detailFragment: DetailFragment

    @Before
    fun setup() {
        detailFragment = DetailFragment()
    }
    @Test
    fun zeroOrNull() {
        Assert.assertEquals(detailFragment.calculateCarbonEmission(null), 0.0, 0.0)
        Assert.assertEquals(detailFragment.calculateCarbonEmission(0), 0.0, 0.0)
    }

    @Test
    fun lessThanEqual5000() {
        Assert.assertEquals(detailFragment.calculateCarbonEmission(1), 1.0, 0.0)
        Assert.assertEquals(detailFragment.calculateCarbonEmission(2500), 2500.0, 0.0)
        Assert.assertEquals(detailFragment.calculateCarbonEmission(4999), 4999.0, 0.0)
        Assert.assertEquals(detailFragment.calculateCarbonEmission(5000), 5000.0, 0.0)
    }

    @Test
    fun moreThan5000() {
        Assert.assertEquals(detailFragment.calculateCarbonEmission(5001), 5001.5, 0.0)
        Assert.assertEquals(detailFragment.calculateCarbonEmission(7000), 8000.0, 0.0)
        Assert.assertEquals(detailFragment.calculateCarbonEmission(85918), 126377.0, 0.0)
        Assert.assertEquals(detailFragment.calculateCarbonEmission(83389), 122583.5, 0.0)
    }
}