package io.github.rool.chatCloneCompose.core.models

import androidx.compose.ui.graphics.Color
import io.github.rool.chatCloneCompose.core.ui.theme.TelegramDefault4

data class Chat(
    val channelId: Int,
    val chatTitle: String,
    val defaultTitle: String,
    val defaultColor: Color,
    val members: Int,
    val onlineMembers: Int,
    val messages: List<Message> = Message.mockedMessages
) {

    companion object {
        var mockedChat: Chat = Chat(0, "TLP Práctica", "TP", TelegramDefault4, 363, 19)
    }
}
