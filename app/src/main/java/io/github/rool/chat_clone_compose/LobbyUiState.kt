package io.github.rool.chat_clone_compose

data class LobbyUiState(val lobbyItems: List<Chat>) {
    companion object {
        val mockedLobbyUiState: LobbyUiState = LobbyUiState((1..20).map { Chat.mockedChat })
    }
}