package io.github.jan.ha.di

import io.github.jan.ha.MainViewModel
import io.github.jan.ha.network.OpenAiApi
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            defaultRequest {
                headers {
                    append("Authorization", "Bearer open-ai-key")
                }
            }
        }
    }
    single { OpenAiApi(get()) }
    viewModel { MainViewModel(get()) }
}