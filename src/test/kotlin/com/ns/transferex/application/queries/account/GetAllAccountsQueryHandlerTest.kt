//package com.ns.transferex.application.queries.account
//
//import com.ns.transferex.application.service.AccountService
//import com.ns.transferex.domain.Account
//import com.ns.transferex.infrastructure.queries.GetAllAccountsQueryHandler
//import com.ns.transferex.infrastructure.queries.account.GetAllAccountsQueryHandler
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.BDDMockito.given
//import org.mockito.InjectMocks
//import org.mockito.Mock
//import org.mockito.junit.MockitoJUnitRunner
//import java.math.BigDecimal
//
//@RunWith(MockitoJUnitRunner::class)
//internal class GetAllAccountsQueryHandlerTest {
//
//    @InjectMocks
//    lateinit var getAllAccountsQueryHandler: GetAllAccountsQueryHandler
//
//    @Mock
//    lateinit var accountService: AccountService
//
//    @Test
//    fun `👼 it should get all accounts  👼`() {
//        //Arrange
//        val allAccountsQuery = GetAllAccountsQuery()
//        val account1 = Account(1, "Enes", BigDecimal.ONE, 0)
//        val account2 = Account(2, "Trabzonspor A.Ş", BigDecimal.TEN, 0)
//        given(accountService.getAll()).willReturn(listOf(account1,account2))
//
//        //Act
//        val expectedResponse = getAllAccountsQueryHandler.handle(allAccountsQuery)
//
//        //Assert
//        assertThat(expectedResponse).hasSize(2)
//        assertThat(expectedResponse[0].owner).isEqualTo("Enes")
//        assertThat(expectedResponse[0].balance).isEqualTo(BigDecimal.ONE)
//        assertThat(expectedResponse[1].owner).isEqualTo("Trabzonspor A.Ş")
//        assertThat(expectedResponse[1].balance).isEqualTo(BigDecimal.TEN)
//    }
//}
//
