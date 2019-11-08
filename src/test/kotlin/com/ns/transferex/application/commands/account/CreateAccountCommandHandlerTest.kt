package com.ns.transferex.application.commands.account

import com.ns.transferex.application.exceptions.BusinessException
import com.ns.transferex.application.persistence.AccountRepository
import com.ns.transferex.domain.Account
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowableOfType
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal


@RunWith(MockitoJUnitRunner::class)
internal class CreateAccountCommandHandlerTest {


    @InjectMocks
    lateinit var createAccountCommandHandler: CreateAccountCommandHandler

    @Mock
    lateinit var accountRepository: AccountRepository


    @Test
    fun `🌹 it should create account 🌹`() {
        //Given
        val createAccountCommand = CreateAccountCommand("Enes", BigDecimal.TEN)

        //When
        createAccountCommandHandler.handle(createAccountCommand)

        //Then
        verify(accountRepository).save(Account.new(createAccountCommand.owner, createAccountCommand.balance))
    }


    @Test
    fun `😨  it should throw business exception when balance is not positive   😨 `() {
        //Given
        val createAccountCommand = CreateAccountCommand("Enes", BigDecimal.valueOf(-61))

        //Whe
        val exception = catchThrowableOfType({ createAccountCommandHandler.handle(createAccountCommand) }, BusinessException::class.java)

        //Then
        verifyZeroInteractions(accountRepository)
        assertThat(exception.message).isEqualTo("Balance must be positive")
    }
}