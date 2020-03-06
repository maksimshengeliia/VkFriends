package com.shengeliia.vkfriends.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class NetUtilsKtTest {

    @Test
    fun checkParamsFromUrl() {
        val url = "https://www.google.com#sara=12321&tara=543"
        val map = getParamsFromUrl(url.substringAfter("#"))
        assertEquals("12321", map["sara"])
        assertEquals("543", map["tara"])
    }
}