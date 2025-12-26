package leegroup.module.gituser.ui.mapper.util

import kotlin.test.Test
import kotlin.test.assertEquals

class FollowerFormatterTest {

    @Test
    fun `test formatLargeNumber with value less than max returns value as string`() {
        // Given
        val value = 50
        val max = 100

        // When
        val result = FollowerFormatter.formatLargeNumber(value, max)

        // Then
        assertEquals("50", result)
    }

    @Test
    fun `test formatLargeNumber with value equal to max returns value as string`() {
        // Given
        val value = 100
        val max = 100

        // When
        val result = FollowerFormatter.formatLargeNumber(value, max)

        // Then
        assertEquals("100", result)
    }

    @Test
    fun `test formatLargeNumber with value greater than max returns max plus`() {
        // Given
        val value = 150
        val max = 100

        // When
        val result = FollowerFormatter.formatLargeNumber(value, max)

        // Then
        assertEquals("100+", result)
    }

    @Test
    fun `test formatLargeNumber with default max of 100`() {
        // Given
        val value = 50

        // When
        val result = FollowerFormatter.formatLargeNumber(value)

        // Then
        assertEquals("50", result)
    }

    @Test
    fun `test formatLargeNumber with value greater than default max`() {
        // Given
        val value = 250

        // When
        val result = FollowerFormatter.formatLargeNumber(value)

        // Then
        assertEquals("100+", result)
    }

    @Test
    fun `test formatLargeNumber with zero value`() {
        // Given
        val value = 0

        // When
        val result = FollowerFormatter.formatLargeNumber(value)

        // Then
        assertEquals("0", result)
    }

    @Test
    fun `test formatLargeNumber with custom max`() {
        // Given
        val value = 1500
        val max = 1000

        // When
        val result = FollowerFormatter.formatLargeNumber(value, max)

        // Then
        assertEquals("1000+", result)
    }

    @Test
    fun `test formatLargeNumber with small custom max`() {
        // Given
        val value = 10
        val max = 5

        // When
        val result = FollowerFormatter.formatLargeNumber(value, max)

        // Then
        assertEquals("5+", result)
    }
}
