//package com.ns.transferex.domain.business
//
//import com.ns.transferex.domain.Transaction
//import com.ns.transferex.application.persistence.TransactionRepository
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.BDDMockito.given
//import org.mockito.InjectMocks
//import org.mockito.Mock
//import org.mockito.junit.MockitoJUnitRunner
//import java.math.BigDecimal
//import java.math.RoundingMode
//import java.util.*
//
//@RunWith(MockitoJUnitRunner::class)
//internal class TransactionServiceImpTest {
//
//
//    @InjectMocks
//    lateinit var transactionServiceImp: TransactionServiceImp
//
//    @Mock
//    lateinit var transactionRepository: TransactionRepository
//
//
//    @Test
//    fun `👱 it should get transaction by id   👱`() {
//        // Given
//        val transaction = Transaction(id = 1, fromAccount = 1, toAccount = 2, amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
//        given(transactionRepository.findById(1)).willReturn(Optional.ofNullable(transaction))
//
//        // When
//        val expectedResult = transactionServiceImp.getTransactionById(1)
//
//        // Then
//        assertThat(expectedResult.id).isEqualTo(1)
//        assertThat(expectedResult.fromAccount).isEqualTo(1)
//        assertThat(expectedResult.toAccount).isEqualTo(2)
//        assertThat(expectedResult.amount).isEqualTo(BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
//    }
//
//
//    @Test
//    fun `👱 it should get all transactions👱`() {
//        // Given
//        val transaction1 = Transaction(id = 1, fromAccount = 1, toAccount = 2, amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
//        val transaction2 = Transaction(id = 2, fromAccount = 2, toAccount = 1, amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
//        given(transactionRepository.findAll()).willReturn(listOf(transaction1,transaction2))
//
//        // When
//        val expectedResult = transactionServiceImp.getAll()
//
//        // Then
//        assertThat(expectedResult).hasSize(2)
//        assertThat(expectedResult[0].id).isEqualTo(1)
//        assertThat(expectedResult[0].fromAccount).isEqualTo(1)
//        assertThat(expectedResult[0].toAccount).isEqualTo(2)
//        assertThat(expectedResult[0].amount).isEqualTo(BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
//
//        assertThat(expectedResult[1].id).isEqualTo(2)
//        assertThat(expectedResult[1].fromAccount).isEqualTo(2)
//        assertThat(expectedResult[1].toAccount).isEqualTo(1)
//        assertThat(expectedResult[1].amount).isEqualTo(BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
//    }
//}