package com.blunderlist.gateway

import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing

fun Application.main() {
  routing {
    get("/") {
      call.respondSuccessJson()
    }
  }
}

private suspend fun ApplicationCall.respondSuccessJson() = respond(TextContent("""{"success": "true"}""", ContentType.Application.Json))
