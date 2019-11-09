//package com.ns.transferex.domain.business
//
//import com.ns.transferex.application.exceptions.BusinessException
//import com.ns.transferex.application.exceptions.DomainNotFoundException
//import com.ns.transferex.domain.Account
//import com.ns.transferex.application.persistence.AccountRepository
//import com.ns.transferex.domain.Transaction
//import com.ns.transferex.application.persistence.TransactionRepository
//import org.assertj.core.api.Assertions.assertThat
//import org.assertj.core.api.Assertions.catchThrowableOfType
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.BDDMockito.given
//import org.mockito.BDDMockito.verifyZeroInteractions
//import org.mockito.InjectMocks
//import org.mockito.Mock
//import org.mockito.Mockito.verify
//import org.mockito.junit.MockitoJUnitRunner
//import java.math.BigDecimal
//import java.math.RoundingMode
//import java.util.*
//
//
//@RunWith(MockitoJUnitRunner::class)
//internal class TransferServiceImpTest {
//
//    @InjectMocks
//    lateinit var transferServiceImp: TransferServiceImp
//
//    @Mock
//    lateinit var accountRepository: AccountRepository
//
//    @Mock
//    lateinit var transactionRepository: TransactionRepository
//
//    @Test
//    fun `💪 it should transfer money successfully 💪`() {
//        //Arrange
//        val transaction = Transaction(fromAccount = 11, toAccount = 12, amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
//
//        val fromAccount = Account(11, "Enes", BigDecimal.TEN)
//        given(accountRepository.findById(11)).willReturn(Optional.ofNullable(fromAccount))
//
//        val toAccount = Account(12, "Ahmet", BigDecimal.TEN)
//        given(accountRepository.findById(12)).willReturn(Optional.ofNullable(toAccount))
//
//
//        //Act
//        transferServiceImp.applyTransfer(transaction)
//
//        //Assert
//        verify(accountRepository).update(fromAccount)
//        verify(accountRepository).update(toAccount)
//        verify(transactionRepository).insert(transaction)
//    }
//
//
//    @Test
//    fun `😫 it should throw domain exception when from account not exist 😫`() {
//        //Arrange
//        val transaction = Transaction(fromAccount = 11, toAccount = 12, amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
//
//        given(accountRepository.findById(11)).willReturn(Optional.empty())
//
//        //Act
//        val exception = catchThrowableOfType({ transferServiceImp.applyTransfer(transaction) }, DomainNotFoundException::class.java)
//
//
//        //Assert
//        verifyZeroInteractions(transactionRepository)
//        assertThat(exception.message).isEqualTo("Account not found")
//    }
//
//
//    @Test
//    fun `😤 it should throw domain exception when to account not exist 😤`() {
//        //Arrange
//        val transaction = Transaction(fromAccount = 11, toAccount = 12, amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
//
//        val fromAccount = Account(11, "Enes", BigDecimal.TEN)
//        given(accountRepository.findById(11)).willReturn(Optional.ofNullable(fromAccount))
//
//        given(accountRepository.findById(12)).willReturn(Optional.empty())
//
//
//        //Act
//        val exception = catchThrowableOfType({ transferServiceImp.applyTransfer(transaction) }, DomainNotFoundException::class.java)
//
//
//        //Assert
//        verifyZeroInteractions(transactionRepository)
//        assertThat(exception.message).isEqualTo("Account not found")
//    }
//
//
//    @Test
//    fun `😡 it should throw business exception when transfer between same accounts  😡`() {
//        //Arrange
//        val transaction = Transaction(fromAccount = 11, toAccount = 12, amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
//
//        val fromAccount = Account(11, "Enes", BigDecimal.TEN)
//        given(accountRepository.findById(11)).willReturn(Optional.ofNullable(fromAccount))
//        given(accountRepository.findById(12)).willReturn(Optional.ofNullable(fromAccount))
//
//
//        //Act
//        val exception = catchThrowableOfType({ transferServiceImp.applyTransfer(transaction) }, BusinessException::class.java)
//
//
//        //Assert
//        verifyZeroInteractions(transactionRepository)
//        assertThat(exception.message).isEqualTo("Transfer must be between different accounts")
//    }
//
//
//    @Test
//    fun `😪 it should throw business exception when account has not enough balance  😪`() {
//        //Arrange
//        val transaction = Transaction(fromAccount = 11, toAccount = 12, amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
//
//        val fromAccount = Account(11, "Enes", BigDecimal.ZERO)
//        given(accountRepository.findById(11)).willReturn(Optional.ofNullable(fromAccount))
//
//        val toAccount = Account(12, "Ahmet", BigDecimal.TEN)
//        given(accountRepository.findById(12)).willReturn(Optional.ofNullable(toAccount))
//
//
//        //Act
//        val exception = catchThrowableOfType({ transferServiceImp.applyTransfer(transaction) }, BusinessException::class.java)
//
//
//        //Assert
//        verifyZeroInteractions(transactionRepository)
//        assertThat(exception.message).isEqualTo("Account has not got enough balance")
//    }
//
//}