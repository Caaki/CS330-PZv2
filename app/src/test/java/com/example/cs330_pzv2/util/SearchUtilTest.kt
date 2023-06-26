package com.example.cs330_pzv2.util

import android.hardware.biometrics.BiometricManager.Strings
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SearchUtilTest{

    @Test
    fun `empty title and tags`(){
        val result = SearchUtil.validateSearch(
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty title with tags`(){
        val result = SearchUtil.validateSearch(
            "",
            "action"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `empty tags with title`(){
        val resutl = SearchUtil.validateSearch(
            "higurashi",
            ""
        )
        assertThat(resutl).isTrue()
    }

    @Test
    fun `test for forbidden strings in title`(){
        val result = SearchUtil.validateSearch(
            "%22",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `test for forbidden strings in tags`(){
        val result = SearchUtil.validateSearch(
            "",
            "aaaa%3Baaaa"
        )
        assertThat(result).isFalse()
    }

}