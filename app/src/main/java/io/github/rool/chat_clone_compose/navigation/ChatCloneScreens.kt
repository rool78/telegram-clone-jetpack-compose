package io.github.rool.chat_clone_compose.navigation

sealed class ChatCloneScreens(val route: String) {
    object Lobby : ChatCloneScreens("lobby")
    object ChatGroup : ChatCloneScreens("chat_group")
}