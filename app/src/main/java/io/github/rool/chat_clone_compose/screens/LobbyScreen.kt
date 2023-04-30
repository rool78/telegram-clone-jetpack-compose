package io.github.rool.chat_clone_compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.rool.chat_clone_compose.Chat
import io.github.rool.chat_clone_compose.LobbyUiState
import io.github.rool.chat_clone_compose.components.DefaultChatImage
import io.github.rool.chat_clone_compose.components.InputIcon
import io.github.rool.chat_clone_compose.navigation.ChatCloneScreens
import io.github.rool.chat_clone_compose.ui.theme.TelegramBlue40
import io.github.rool.chat_clone_compose.ui.theme.TelegramDefault1
import io.github.rool.chat_clone_compose.ui.theme.TelegramDefault3
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyScreen(navController: NavController) {
    val uiState = LobbyUiState.MockedState.lobbyUiMockedState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalNavigationDrawer(
        modifier = Modifier.clip(RoundedCornerShape(0.dp)),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .background(TelegramBlue40)
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    DefaultChatImage(
                        Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(TelegramDefault3), "ME"
                    )
                    Text(text = "rooL")
                    Text(text = "+34 712 123 123")
                }
                uiState.drawerItems.forEach { item ->
                    DrawerMenuItem(imageVector = item.icon, text = item.text)
                }
            }
        }) {
        Scaffold(topBar = {
            LobbyTopBar {
                scope.launch {
                    drawerState.open()
                }
            }
        },
            content = { paddingValues ->
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    LobbyContent(navController, uiState.lobbyItems)
                }
            })
    }
}

@Composable
private fun DrawerMenuItem(
    imageVector: ImageVector,
    text: String,
    onItemClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text)
    }
}


@Composable
fun LobbyTopBar(onMenuIconClick: () -> Unit) {
    Row(
        modifier = Modifier
            .background(TelegramBlue40)
            .padding(vertical = 4.dp)
    ) {
        InputIcon(
            onClick = onMenuIconClick,
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
fun LobbyContent(navController: NavController, lobbyItems: List<Chat>) {
    LazyColumn {
        itemsIndexed(lobbyItems) { index, chat ->
            LobbyChatItem(chat) { navController.navigate(ChatCloneScreens.ChatGroup.route) }
            if (index < lobbyItems.size - 1) {
                Divider(modifier = Modifier.padding(horizontal = 8.dp))
            }
        }
    }
}

@Composable
fun LobbyChatItem(chat: Chat, onItemClick: () -> Unit) {
    val lastMessage = chat.messages.last()
    Row(modifier = Modifier
        .clickable { onItemClick() }
        .padding(vertical = 5.dp)) {
        DefaultChatImage(
            Modifier
                .padding(4.dp)
                .align(Alignment.Bottom)
                .size(40.dp)
                .clip(CircleShape)
                .background(TelegramDefault1), chat.defaultTitle
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(text = chat.chatTitle)
            Text(text = lastMessage.content)
        }
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = lastMessage.timestamp)
        }
    }
}
