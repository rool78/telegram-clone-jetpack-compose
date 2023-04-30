package io.github.rool.chat_clone_compose

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatGroupViewModel @Inject constructor() : ViewModel() {

    private val _chatUiState = MutableStateFlow(ChatUiState.mockedUiState)
    val chatUiState = _chatUiState.asStateFlow()

    fun sendMessage(content: String) {
        val messages = _chatUiState.value.chat.messages.plus(content.toMessage())
        _chatUiState.value =
            _chatUiState.value.copy(chat = _chatUiState.value.chat.copy(messages = messages))
    }

    private fun String.toMessage(): Message =
        Message(
            Message.AUTHOR_NAME,
            Color.Transparent,
            this,
            "00:00 am" //TODO Parse current time
        )
}
