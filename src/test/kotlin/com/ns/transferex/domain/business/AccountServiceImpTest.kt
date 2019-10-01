package com.ns.transferex.domain.business

import com.ns.transferex.domain.Account
import com.ns.transferex.domain.AccountRepository
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.any
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.given
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal
import java.util.*

@RunWith(MockitoJUnitRunner::class)
internal class AccountServiceImpTest {

    @InjectMocks
    lateinit var accountService: AccountServiceImp

    @Mock
    lateinit var accountRepository: AccountRepository

    @Test
    fun `🙆 it should get account by id  🙆`() {
        // Given
        val account = Account(1, "Enes", BigDecimal.TEN, 0)
        given(accountRepository.findById(1)).willReturn(Optional.ofNullable(account))

        // When
        val expectedResult = accountService.getAccountById(1)

        // Then
        assertThat(expectedResult.id).isEqualTo(1)
        assertThat(expectedResult.owner).isEqualTo("Enes")
        assertThat(expectedResult.balance).isEqualTo(BigDecimal.TEN)
    }


    @Test
    fun `🙅 it should get all accounts  🙅`() {
        // Given
        val account1 = Account(1, "Enes", BigDecimal.ONE, 0)
        val account2 = Account(2, "Trabzonspor A.Ş", BigDecimal.TEN, 0)
        given(accountRepository.findAll()).willReturn(listOf(account1, account2))

        // When
        val expectedResponse = accountService.getAll()

        // Then
        assertThat(expectedResponse).hasSize(2)
        assertThat(expectedResponse[0].owner).isEqualTo("Enes")
        assertThat(expectedResponse[0].balance).isEqualTo(BigDecimal.ONE)
        assertThat(expectedResponse[1].owner).isEqualTo("Trabzonspor A.Ş")
        assertThat(expectedResponse[1].balance).isEqualTo(BigDecimal.TEN)
    }

    @Test
    fun `💁 it should save account  💁`() {
        // Given
        val account = Account(1, "Enes", BigDecimal.TEN, 0)

        // When
        accountService.save(account)

        // Then
        verify(accountRepository,times(1)).insert(account)
    }


    @Test
    fun `🙋 it should update account  🙋`() {
        // Given
        val account = Account(1, "Enes", BigDecimal.TEN, 0)

        // When
        accountService.update(account)

        // Then
        verify(accountRepository,times(1)).update(account)
    }
}