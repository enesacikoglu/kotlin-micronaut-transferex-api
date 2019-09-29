package com.ns.transferex.application.web


import com.ns.transferex.domain.Account
import com.ns.transferex.domain.models.GetAccountByIdResponse
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
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


open class AccountControllerTest {


    lateinit var server: EmbeddedServer

    lateinit var client: HttpClient

    @BeforeEach
    fun setupServer() {
        server = ApplicationContext.run(EmbeddedServer::class.java)
        client = server.applicationContext.createBean(HttpClient::class.java, server.uri)
    }


    @Test
    fun ` 😇 it should get account by id  😇`() {
        //Given
        val post = HttpRequest.POST(URI.create("/accounts"), Account(owner = "Enes", balance = BigDecimal.TEN.setScale(2,RoundingMode.HALF_DOWN)))
        val get = HttpRequest.GET<URI>(URI.create("/accounts/1"))

        client.toBlocking().exchange(post, Account::class.java) // Account create


        //When
        val retrieveAccount = client.toBlocking().retrieve(get, GetAccountByIdResponse::class.java)


        //Then
        assertNotNull(retrieveAccount)
        assertThat(retrieveAccount.id).isEqualTo(1)
        assertThat(retrieveAccount.owner).isEqualTo("Enes")
        assertThat(retrieveAccount.balance).isEqualTo(BigDecimal.TEN.setScale(2,RoundingMode.HALF_DOWN))
    }


    @AfterEach
    fun stopServer() {
        if (server != null) {
            server.stop()
        }
        if (client != null) {
            client.stop()
        }
    }
}