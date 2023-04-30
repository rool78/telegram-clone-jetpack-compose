package io.github.rool.chat_clone_compose

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ChatGroupViewModel @Inject constructor() : ViewModel() {

    private val _chatUiState = MutableStateFlow(ChatUiState.mockedUiState)
    val chatUiState = _chatUiState.asStateFlow()

    fun sendMessage(content: String) {
        val messages = _chatUiState.value.chat.messages.plus(content.toMessage()).also {
            Chat.mockedChat = Chat.mockedChat.copy(messages = it)
        }
        ChatUiState.mockedUiState =
            _chatUiState.value.copy(chat = _chatUiState.value.chat.copy(messages = messages)).also {
                _chatUiState.value = it
            }
    }

    private fun String.toMessage(): Message =
        Message(
            Message.AUTHOR_NAME,
            Color.Transparent,
            this,
            SimpleDateFormat("hh:mm", Locale.getDefault()).format(Calendar.getInstance().time)
        )
}
