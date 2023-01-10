package io.github.jan.ha

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.ha.network.OpenAiApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val openAiApi: OpenAiApi
): ViewModel() {

    val response = MutableStateFlow<String?>(null)
    val loading = MutableStateFlow(false)

    fun retrieveCompletion(prompt: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                loading.value = true
                openAiApi.retrieveCompletion(prompt)
            }.onSuccess {
                response.value = it
            }.onFailure {
                it.printStackTrace()
            }
            loading.value = false
        }
    }

}