package io.github.rool.chat_clone_compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Man
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.ui.graphics.vector.ImageVector

data class LobbyUiState(val drawerItems: List<DrawerItem>, val lobbyItems: List<Chat>) {
    companion object {
        private val mockedDrawerItem: List<DrawerItem> = listOf(
            DrawerItem(Icons.Filled.Group, "New group"),
            DrawerItem(Icons.Filled.Person, "Contacts"),
            DrawerItem(Icons.Filled.Call, "Calls"),
            DrawerItem(Icons.Filled.NearMe, "Near me"),
            DrawerItem(Icons.Filled.Man, "Saved messages"),
            DrawerItem(Icons.Filled.Settings, "Settings"),
            DrawerItem(Icons.Filled.VerifiedUser, "Invite friends"),
            DrawerItem(Icons.Filled.Info, "About me")
        )
        val mockedLobbyUiState: LobbyUiState =
            LobbyUiState(mockedDrawerItem, (1..20).map { Chat.mockedChat })
    }
}

data class DrawerItem(val icon: ImageVector, val text: String)