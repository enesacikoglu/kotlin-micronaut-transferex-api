package com.ns.transferex.application.web

import com.ns.transferex.application.commands.transaction.CreateTransactionCommand
import com.ns.transferex.domain.Account
import com.ns.transferex.domain.Transaction
import com.ns.transferex.domain.models.GetTransactionByIdResponse
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import junit.framework.Assert.assertNotNull
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.URI

internal class TransactionControllerITTest {

    lateinit var server: EmbeddedServer

    lateinit var client: HttpClient

    @BeforeEach
    fun setupServer() {
        server = ApplicationContext.run(EmbeddedServer::class.java)
        client = server.applicationContext.createBean(HttpClient::class.java, server.uri)
    }

    @Test
    fun ` 🙈 it should get transaction by id   🙈`() {
        //Given
        val post = HttpRequest.POST(URI.create("/transactions"), CreateTransactionCommand(fromAccount = 2, toAccount = 3, amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN)))
        val get = HttpRequest.GET<URI>(URI.create("/transactions/1"))

        client.toBlocking().exchange(post, CreateTransactionCommand::class.java) // Transaction create


        //When
        val retrieveTransaction = client.toBlocking().retrieve(get, GetTransactionByIdResponse::class.java)


        //Then
        assertNotNull(retrieveTransaction)
        assertThat(retrieveTransaction.id).isEqualTo(1)
        assertThat(retrieveTransaction.fromAccount).isEqualTo(2)
        assertThat(retrieveTransaction.toAccount).isEqualTo(3)
        assertThat(retrieveTransaction.amount).isEqualTo(BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
    }


    @Test
    fun `🐝 it should get all transactions  🐝`() {
        //Given
        val transaction1 = HttpRequest.POST(URI.create("/transactions"), CreateTransactionCommand(fromAccount = 2, toAccount = 3, amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN)))
        val transaction2 = HttpRequest.POST(URI.create("/transactions"), CreateTransactionCommand(fromAccount = 3, toAccount = 2, amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN)))

        val get = HttpRequest.GET<URI>(URI.create("/transactions/1"))


        client.toBlocking().exchange(transaction1, CreateTransactionCommand::class.java) // Transaction create
        client.toBlocking().exchange(transaction2, CreateTransactionCommand::class.java) // Transaction create


        //When
        val response = client.toBlocking().retrieve(get, List::class.java as Class<*>)

        //Then
        Assert.assertNotNull(response)
    }


    @Test
    fun ` 😇it should create transaction😇`() {
        //Given
        val transaction = HttpRequest.POST(URI.create("/transactions"), CreateTransactionCommand(fromAccount = 2, toAccount = 3, amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN)))


        //When
        val repsonse: HttpResponse<Account> = client.toBlocking().exchange(transaction, Account::class.java)

        //Then
        assertThat(repsonse.status).isEqualTo(HttpStatus.OK)
    }

    @AfterEach
    fun stopServer() {
        server.stop()
        client.stop()
    }

}