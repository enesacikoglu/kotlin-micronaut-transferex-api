package com.ns.transferex.infrastructure.web

import com.ns.transferex.application.commands.account.CreateAccountCommand
import com.ns.transferex.application.commands.transfer.TransferOneAccountToAnotherCommand
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.URI

internal class TransferControllerITTest {

    lateinit var server: EmbeddedServer

    lateinit var client: HttpClient

    @BeforeEach
    fun setupServer() {
        server = ApplicationContext.run(EmbeddedServer::class.java)
        client = server.applicationContext.createBean(HttpClient::class.java, server.uri)
    }


    @Test
    fun ` 👸 it should apply transfer 👸`() {
        //Given

        val account1 = HttpRequest.POST(URI.create("/accounts"), CreateAccountCommand(owner = "Enes", balance = BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN)))
        val account2 = HttpRequest.POST(URI.create("/accounts"), CreateAccountCommand(owner = "Hakan", balance = BigDecimal.ONE.setScale(2, RoundingMode.HALF_DOWN)))
        client.toBlocking().exchange(account1, CreateAccountCommand::class.java) // Account create1
        client.toBlocking().exchange(account2, CreateAccountCommand::class.java) // Account create2

        val transferOneAccountToAnotherCommand = TransferOneAccountToAnotherCommand(1, 2, BigDecimal.TEN.setScale(2, RoundingMode.HALF_DOWN))
        val transferOneAccountToAnotherRequest = HttpRequest.POST(URI.create("/transactions"), transferOneAccountToAnotherCommand)


        //When
        val response = client.toBlocking().exchange(transferOneAccountToAnotherRequest, TransferOneAccountToAnotherCommand::class.java)

        //Then
        assertThat(response.status).isEqualTo(HttpStatus.OK)
    }


    @AfterEach
    fun stopServer() {
        server.stop()
        client.stop()
    }

}