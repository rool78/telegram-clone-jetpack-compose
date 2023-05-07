package io.github.rool.chatCloneCompose.features.lobby

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.rool.chatCloneCompose.core.models.Chat
import io.github.rool.chatCloneCompose.core.navigation.ChatCloneScreens
import io.github.rool.chatCloneCompose.core.ui.components.NotAvailablePopUpVisibility
import io.github.rool.chatCloneCompose.core.ui.components.NotAvailablePopup
import io.github.rool.chatCloneCompose.core.ui.components.isVisible
import io.github.rool.chatCloneCompose.core.ui.theme.TelegramBlue40
import io.github.rool.chatCloneCompose.core.ui.theme.TelegramBlue80
import io.github.rool.chatCloneCompose.core.ui.theme.TelegramGreen50
import io.github.rool.chatCloneCompose.core.ui.theme.TelegramGrey50
import io.github.rool.chatCloneCompose.features.chatGroup.components.DefaultChatImage
import io.github.rool.chatCloneCompose.features.chatGroup.components.InputIcon
import io.github.rool.chat_clonse_compose.R
import kotlinx.coroutines.launch

@Composable
fun LobbyScreen(navController: NavController) {
    val uiState = LobbyUiState.MockedState.lobbyUiMockedState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .background(TelegramBlue40)
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_pic),
                        contentDescription = stringResource(
                            id = R.string.user_profile_pic_description
                        ),

                        modifier = Modifier
                            .padding(8.dp)
                            .size(64.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop

                    )
                    Text(
                        text = uiState.userName,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = uiState.userPhone,
                        color = TelegramBlue80,
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                uiState.drawerItems.forEach { item ->
                    DrawerMenuItem(imageVector = item.icon, text = item.text)
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
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
            }
        )
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
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text)
    }
}

@Composable
fun LobbyTopBar(onMenuIconClick: () -> Unit) {
    var notAvailablePopupVisibility by rememberSaveable {
        mutableStateOf(
            NotAvailablePopUpVisibility.GONE
        )
    }
    if (notAvailablePopupVisibility.isVisible()) {
        NotAvailablePopup { notAvailablePopupVisibility = NotAvailablePopUpVisibility.GONE }
    }
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
            text = "Jetpack Compose Telegram",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        InputIcon(
            onClick = { notAvailablePopupVisibility = NotAvailablePopUpVisibility.VISIBLE },
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
    Row(
        modifier = Modifier
            .clickable { onItemClick() }
            .padding(start = 8.dp, top = 8.dp)
    ) {
        DefaultChatImage(
            Modifier
                .padding(4.dp)
                .align(Alignment.Bottom)
                .size(44.dp)
                .clip(CircleShape)
                .background(chat.defaultColor),
            chat.defaultTitle
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = chat.chatTitle,
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = lastMessage.content,
                color = TelegramGrey50
            )
        }
        Image(
            imageVector = Icons.Filled.Done,
            contentDescription = null,
            colorFilter = ColorFilter.tint(TelegramGreen50),
            modifier = Modifier
                .size(24.dp)
                .padding(start = 4.dp)
        )
        Text(
            text = lastMessage.timestamp,
            color = TelegramGrey50,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(4.dp)
        )
    }
}
