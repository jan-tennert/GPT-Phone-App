package io.github.jan.ha.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class OpenAiApi(private val httpClient: HttpClient) {

    companion object {
        private const val COMPLETIONS_URL = "https://api.openai.com/v1/completions"
        private const val DEFAULT_MODEL = "text-davinci-003"
        private const val DEFAULT_MAX_TOKENS = 1000
    }

    suspend fun retrieveCompletion(prompt: String): String {
        val response = httpClient.post(COMPLETIONS_URL) {
            contentType(ContentType.Application.Json)
            setBody(OpenAiRequest(prompt, DEFAULT_MODEL, DEFAULT_MAX_TOKENS))
        }.body<OpenAiResponse>()
        return response.choices.first().text
    }

}