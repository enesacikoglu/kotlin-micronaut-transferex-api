package com.ns.transferex.application.web


import com.ns.transferex.application.commands.account.CreateAccountCommand
import com.ns.transferex.domain.Account
import com.ns.transferex.domain.models.GetAccountByIdResponse
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertNotNull
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.URI


internal class AccountControllerITTest {


    lateinit var server: EmbeddedServer

    lateinit var client: HttpClient

    @BeforeEach
    fun setupServer() {
        server = ApplicationContext.run(EmbeddedServer::class.java)
        client = server.applicationContext.createBean(HttpClient::class.java, server.uri)
    }


    @Test
    fun `💝 it should get account by id    💝`() {
        //Given
        val post = HttpRequest.POST(URI.create("/accounts"), CreateAccountCommand(owner = "Enes", balance = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN)))
        val get = HttpRequest.GET<URI>(URI.create("/accounts/1"))


        client.toBlocking().exchange(post, CreateAccountCommand::class.java) // Account create


        //When
        val retrieveAccount = client.toBlocking().retrieve(get, GetAccountByIdResponse::class.java)


        //Then
        assertNotNull(retrieveAccount)
        assertThat(retrieveAccount.id).isEqualTo(1)
        assertThat(retrieveAccount.owner).isEqualTo("Enes")
        assertThat(retrieveAccount.balance).isEqualTo(BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
    }


    @Test
    fun `🐮  it should get all accounts   🐮`() {
        //Given
        val postAccount1 = HttpRequest.POST(URI.create("/accounts"), CreateAccountCommand(owner = "Enes", balance = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN)))
        val postAccount2 = HttpRequest.POST(URI.create("/accounts"), CreateAccountCommand(owner = "Hakan", balance = BigDecimal.ONE.setScale(2, RoundingMode.HALF_DOWN)))
        val get = HttpRequest.GET<URI>(URI.create("/accounts"))

        client.toBlocking().exchange(postAccount1, CreateAccountCommand::class.java) // Account create
        client.toBlocking().exchange(postAccount2, CreateAccountCommand::class.java) // Account create


        //When
        val response = client.toBlocking().retrieve(get, List::class.java as Class<*>)

        //Then
        assertNotNull(response)
    }


    @Test
    fun ` 🚜 it should create account   🚜`() {
        //Given
        val createAccountResponse = HttpRequest.POST(URI.create("/accounts"), Account(id = 1, owner = "Enes", balance = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN)))


        //When
        val repsonse: HttpResponse<Account> = client.toBlocking().exchange(createAccountResponse, Account::class.java)

        //Then
        assertThat(repsonse.status).isEqualTo(HttpStatus.OK)
    }


    @AfterEach
    fun stopServer() {
        server.stop()
        client.stop()
    }
}