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
internal class GetAllTransactionsQueryHandlerTest {

    @InjectMocks
    lateinit var getAllTransactionsQueryHandler: GetAllTransactionsQueryHandler

    @Mock
    lateinit var transactionService: TransactionService

    @Test
    fun `it should get all transactions`() {
        //Arrange
        val getAllTransactionsQuery = GetAllTransactionsQuery()
        val transaction = Transaction(1, 61, 62, BigDecimal.TEN)
        given(transactionService.getAll()).willReturn(listOf(transaction))

        //Act
        val expectedResponse = getAllTransactionsQueryHandler.handle(getAllTransactionsQuery)

        //Assert
        assertThat(expectedResponse).hasSize(1)
        assertThat(expectedResponse[0].fromAccount).isEqualTo(61)
        assertThat(expectedResponse[0].toAccount).isEqualTo(62)
        assertThat(expectedResponse[0].amount).isEqualTo(BigDecimal.TEN)
    }
}