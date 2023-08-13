package com.example.task.utils


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegisterUtilsTest {
    @Test
    fun `empty user name returns false`() {
        val result = RegisterUtils.validateRegisterInput("", "123", "123")
        assertThat(result).isFalse()
    }

    @Test
    fun `valid user name and correclty repeated password returns true`() {
        val result = RegisterUtils.validateRegisterInput("java", "123", "123")
        assertThat(result).isTrue()
    }

    @Test
    fun `user name already exist returns false`() {
        val result = RegisterUtils.validateRegisterInput("android", "123", "123")
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`() {
        val result = RegisterUtils.validateRegisterInput("java", "", "")
        assertThat(result).isFalse()
    }

    @Test
    fun `incorrectly confirm password returns false`() {
        val result = RegisterUtils.validateRegisterInput("java", "123", "1234")
        assertThat(result).isFalse()
    }
}