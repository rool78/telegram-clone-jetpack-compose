package io.github.rool.chat_clone_compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.rool.chat_clone_compose.LobbyUiState
import io.github.rool.chat_clone_compose.components.DefaultChatImage
import io.github.rool.chat_clone_compose.components.InputIcon
import io.github.rool.chat_clone_compose.ui.theme.TelegramBlue40
import io.github.rool.chat_clone_compose.ui.theme.TelegramDefault1
import io.github.rool.chat_clone_compose.ui.theme.TelegramGrey50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyScreen() {
    Scaffold(topBar = { LobbyTopBar() }) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LobbyContent()

        }
    }
}

@Composable
fun LobbyTopBar() {
    Row(
        modifier = Modifier
            .background(TelegramBlue40)
            .padding(vertical = 4.dp)
    ) {
        InputIcon(
            onClick = { /*TODO*/ },
            icon = Icons.Filled.Menu,
            description = "icon menu",
            tint = Color.White
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            color = Color.White,
            text = "Jetpack Compose Telegram"
        )
        InputIcon(
            onClick = { /*TODO*/ },
            icon = Icons.Filled.Search,
            description = "icon menu",
            tint = Color.White
        )
    }

}

@Composable
fun LobbyContent() {
    val uiState = LobbyUiState.mockedLobbyUiState
    LazyColumn {
        items(uiState.lobbyItems) {
            LobbyChatItem()
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(TelegramGrey50)
            )
        }
    }
}

@Composable
fun LobbyChatItem() {
    Row(modifier = Modifier.padding(vertical = 5.dp)) {
        DefaultChatImage(
            Modifier
                .padding(4.dp)
                .align(Alignment.Bottom)
                .size(40.dp)
                .clip(CircleShape)
                .background(TelegramDefault1), "L"
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(text = "Laura")
            Text(text = "See you!")
        }
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "15:12")
        }
    }
}
