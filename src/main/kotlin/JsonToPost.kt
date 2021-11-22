import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*

import io.ktor.client.features.logging.*

const val URL0 = "https://raw.githubusercontent.com/Svinsborg/jsonfiles/main/autocreate.json"
const val URL1 = "https://viktorov.ml/res/doc/AutoCreate.json"
const val URL2 = "https://95.165.135.238/res/doc/AutoCreate.json"

object JsonToPost {
    suspend fun connection(): List<Post> {
        val client = HttpClient {
            install(JsonFeature) {

                acceptContentTypes = listOf(
                    ContentType.Text.Plain,
                    ContentType.Application.Json
                )
                serializer = GsonSerializer()
            }
/*            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }*/
        }
        return try {
            client.get {
                url(URL0)
            }
        } catch (err: RedirectResponseException) {
            println("Error redirect: ${err.response.status.description}")
            emptyList()
        } catch (err: ClientRequestException) {
            println("Error client: ${err.response.status.description}")
            emptyList()
        } catch (err: ServerResponseException) {
            println("Error server: ${err.response.status.description}")
            emptyList()
        } catch (err: Exception) {
            println("Error unknown: ${err.message}")
            emptyList()
        }

    }
}
