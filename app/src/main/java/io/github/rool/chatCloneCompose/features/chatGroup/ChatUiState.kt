package io.github.rool.chatCloneCompose.features.chatGroup

import io.github.rool.chatCloneCompose.core.models.Chat

data class ChatUiState(val chat: Chat) {
    companion object {
        var mockedUiState: ChatUiState = ChatUiState(Chat.mockedChat)
    }
}
