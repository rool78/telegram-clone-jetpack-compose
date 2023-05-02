package io.github.rool.chat_clone_compose.features.chat_group

import io.github.rool.chat_clone_compose.core.models.Chat

data class ChatUiState(val chat: Chat) {
    companion object {
        var mockedUiState: ChatUiState = ChatUiState(Chat.mockedChat)
    }
}
