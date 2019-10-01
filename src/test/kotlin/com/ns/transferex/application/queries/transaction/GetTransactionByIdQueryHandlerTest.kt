package com.ns.transferex.application.queries.transaction

import com.ns.transferex.application.service.TransactionService
import com.ns.transferex.domain.Transaction
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
internal class GetTransactionByIdQueryHandlerTest {

    @InjectMocks
    lateinit var getTransactionByIdQueryHandler: GetTransactionByIdQueryHandler

    @Mock
    lateinit var transactionService: TransactionService

    @Test
    fun `it should get transaction by id`() {
        //Arrange
        val getTransactionByIdQuery = GetTransactionByIdQuery(1)
        val transaction = Transaction(1, 61, 62, BigDecimal.ONE)
        given(transactionService.getTransactionById(1)).willReturn(transaction)

        //Act
        val expectedResponse = getTransactionByIdQueryHandler.handle(getTransactionByIdQuery)

        //Assert
        assertThat(expectedResponse?.fromAccount).isEqualTo(61)
        assertThat(expectedResponse?.toAccount).isEqualTo(62)
        assertThat(expectedResponse?.amount).isEqualTo(BigDecimal.ONE)
    }

}