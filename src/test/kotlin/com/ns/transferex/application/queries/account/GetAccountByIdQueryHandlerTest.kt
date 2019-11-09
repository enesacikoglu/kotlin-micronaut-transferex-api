package com.ns.transferex.application.queries.account

import com.ns.transferex.domain.Account
import com.ns.transferex.infrastructure.queries.account.GetAccountByIdQueryHandler
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal
import javax.persistence.EntityManager

@RunWith(MockitoJUnitRunner::class)
internal class GetAccountByIdQueryHandlerTest {

    @InjectMocks
    lateinit var getAccountByIdQueryHandler: GetAccountByIdQueryHandler
    
    @Mock
    lateinit var entityManager: EntityManager;

    @Test
    fun `👰 it should get account by id  👰`() {
        //Arrange
        val idQuery = GetAccountByIdQuery(1)
        val account = Account(1, "Enes", BigDecimal.TEN, 0)
        given(entityManager.createQuery(ArgumentMatchers.anyString()).singleResult).willReturn(account)

        //Act
        val expectedResponse = getAccountByIdQueryHandler.handle(idQuery)

        //Assert
        assertThat(expectedResponse?.id).isEqualTo(1)
        assertThat(expectedResponse?.owner).isEqualTo("Enes")
        assertThat(expectedResponse?.balance).isEqualTo(BigDecimal.TEN)
    }
}

