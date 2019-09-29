package com.ns.transferex.domain.business

import com.ns.transferex.domain.Account
import com.ns.transferex.infrastructure.persistance.AccountRepositoryImp
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class AccountServiceImpTest {

    @InjectMocks
    lateinit var accountServiceImp: AccountServiceImp

    @Mock
    lateinit var accountRepositoryImp: AccountRepositoryImp


    @Test
    fun `it should get account by id`() {
        // Given
        val account = Account(1, "Enes", BigDecimal.TEN, 0)
        given(accountRepositoryImp.findById(1)).willReturn(Optional.ofNullable(account))

        // When
        val expectedResult = accountServiceImp.getAccountById(1)

        // Then
        assertThat(expectedResult.id).isEqualTo(1)
        assertThat(expectedResult.owner).isEqualTo("Enes")
        assertThat(expectedResult.balance).isEqualTo(BigDecimal.TEN)
    }
}