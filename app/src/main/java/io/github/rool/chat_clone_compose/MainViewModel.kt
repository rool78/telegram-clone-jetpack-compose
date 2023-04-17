package io.github.rool.chat_clone_compose

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _chatUiState = MutableStateFlow(ChatUiState.mockedUiState)
    val chatUiState = _chatUiState.asStateFlow()

    fun sendMessage(content: String) {
        val messages = _chatUiState.value.messages.plus(content.toMessage())
        _chatUiState.value = _chatUiState.value.copy(messages = messages)
    }

    private fun String.toMessage(): Message =
        Message(
            Message.AUTHOR_NAME,
            Color.Transparent,
            this,
            "00:00 am" //TODO Parse current time
        )
}
