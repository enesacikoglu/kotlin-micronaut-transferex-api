package com.ns.transferex.application.commands.transfer

import com.ns.transferex.application.exceptions.BusinessException
import com.ns.transferex.application.persistence.AccountRepository
import com.ns.transferex.application.persistence.TransactionRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyZeroInteractions
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
internal class TransferOneAccountToAnotherCommandHandlerTest {

    @InjectMocks
    lateinit var transferOneAccountToAnotherCommandHandler: TransferOneAccountToAnotherCommandHandler

    @Mock
    lateinit var accountRepository: AccountRepository

    @Mock
    lateinit var transactionRepository: TransactionRepository
    


    @Test
    fun `😁 it should apply transfer  😁`() {
        //Given
        val transferOneAccountToAnotherCommand = TransferOneAccountToAnotherCommand(4, 5, BigDecimal.TEN)

        //When
        transferOneAccountToAnotherCommandHandler.handle(transferOneAccountToAnotherCommand)

        //Then
     //   verify(transferService).applyTransfer(Transaction.new(transferOneAccountToAnotherCommand.fromAccount, transferOneAccountToAnotherCommand.toAccount, transferOneAccountToAnotherCommand.amount))
    }


    @Test
    fun `😱 it should throw business exception when balance is not positive 😱`() {

        //Given
        val transferOneAccountToAnotherCommand = TransferOneAccountToAnotherCommand(4, 5, BigDecimal.valueOf(-1))

        //When
        val exception = Assertions.catchThrowableOfType({ transferOneAccountToAnotherCommandHandler.handle(transferOneAccountToAnotherCommand) }, BusinessException::class.java)


        //Then
        verifyZeroInteractions(transactionRepository)
        assertThat(exception.message).isEqualTo("Transfer amount must be positive")
    }
}