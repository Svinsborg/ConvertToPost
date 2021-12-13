package ru.hell.app_request

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.features.logging.*

const val URL0 = "https://raw.githubusercontent.com/Svinsborg/jsonfiles/main/autocreate.json"
const val URL1 = "https://viktorov.ml/res/doc/AutoCreate.json"
const val URL2 = "https://95.165.135.238/res/doc/AutoCreate.json"
const val URL3 = "http://viktorov.ml:8008/post"

object JsonToPost {
    suspend fun connection(): List<Post> {
        val client = HttpClient() {

            engine {
                threadsCount = 4
                pipelining = true
            }

            install(JsonFeature) {

                serializer = GsonSerializer()

                acceptContentTypes = listOf(
                    ContentType.Text.Plain,
                    ContentType.Application.Json
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }

        return try {
            client.get {
                url(URL3)
            }
        } catch (err: RedirectResponseException) {
            println("->> Error redirect: ${err.response.status.description}")
            emptyList()
        } catch (err: ClientRequestException) {
            println("->> Error client: ${err.response.status.description}")
            emptyList()
        } catch (err: ServerResponseException) {
            println("->> Error server: ${err.response.status.description}")
            emptyList()
        } catch (err: Exception) {
            println("->> Error others: ${err.message}")
            emptyList()
        }

   }
}
