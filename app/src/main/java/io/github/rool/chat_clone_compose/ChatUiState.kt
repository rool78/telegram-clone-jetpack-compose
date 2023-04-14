package io.github.rool.chat_clone_compose

data class ChatUiState(
    val chatTitle: String,
    val members: Int,
    val onlineMembers: Int
) {
    companion object {
        val mockedUiState: ChatUiState = ChatUiState("TLP Project", 363, 19)
    }
}
