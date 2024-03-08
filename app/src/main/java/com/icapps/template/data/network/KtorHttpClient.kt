import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import okhttp3.OkHttpClient

private const val TIME_OUT = 60_000

val buildKtorHttpClient: (baseUrl: String, okHttpClient: OkHttpClient) -> HttpClient = { baseUrl, okHttpClient ->
    HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json()
        }

        defaultRequest {
            url(baseUrl)
        }

        engine {
            preconfigured = okHttpClient
        }
    }
}
