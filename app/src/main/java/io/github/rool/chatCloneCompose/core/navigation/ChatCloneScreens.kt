package io.github.rool.chatCloneCompose.core.navigation

sealed class ChatCloneScreens(val route: String) {
    object Lobby : ChatCloneScreens("lobby")
    object ChatGroup : ChatCloneScreens("chat_group")
}
