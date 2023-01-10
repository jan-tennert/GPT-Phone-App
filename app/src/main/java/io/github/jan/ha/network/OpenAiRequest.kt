package io.github.jan.ha.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenAiRequest(
    val prompt: String,
    val model: String,
    @SerialName("max_tokens")
    val maxTokens: Int,
)