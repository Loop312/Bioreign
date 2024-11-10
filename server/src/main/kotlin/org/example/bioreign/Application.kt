package org.example.bioreign

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.runBlocking
import io.ktor.server.websocket.WebSockets
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map

val client = HttpClient(CIO) {
    install(io.ktor.client.plugins.websocket.WebSockets){
        pingIntervalMillis = 20_000
    }
}
var clients = 0
fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = false)

    embeddedServer(Netty, port = 8081, host = "0.0.0.0"){
        install(WebSockets)
        routing {
            get("/ws"){
                call.respondText("Hello from port 8081, the websocket")
            }
            webSocket("/ws") {
                println("Connected to port 8081")
                while(true) {
                    incoming.consumeAsFlow().map { it as Frame.Text }.collect { println(it.readText()) }
                    delay(1000/60)
                }
            }
        }
    }.start(wait = false)
    runBlocking {
        client.webSocket(
            method = HttpMethod.Get,
            host = "127.0.0.1",
            port = 8081,
            path = "/ws"
        ) {
            clients++
            while (true) {
                /*
                for(frame in incoming) {
                    println(frame)
                    frame as? Frame.Text ?: continue
                    val receivedText = frame.readText()
                    println(receivedText)
                }

                 */
                send("(${player.x},${player.y})")
                delay(1000/60)
            }
        }
    }
    client.close()
}

fun Application.module() {
    routing {
        get("/") {
            call.respond("Hello from port $SERVER_PORT")
        }
    }
}