package io.github.rool.chat_clone_compose.features.chat_group.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.rool.chat_clone_compose.core.ui.components.NotAvailablePopUpVisibility
import io.github.rool.chat_clone_compose.core.ui.components.NotAvailablePopup
import io.github.rool.chat_clone_compose.core.ui.components.isVisible
import io.github.rool.chat_clone_compose.core.ui.theme.TelegramBlue40
import io.github.rool.chat_clone_compose.core.ui.theme.TelegramBlue80
import io.github.rool.chat_clone_compose.features.chat_group.ChatUiState
import io.github.rool.chat_clonse_compose.R

@Composable
fun ChatTopBar(navController: NavController, uiState: ChatUiState) {
    var notAvailablePopupVisibility by rememberSaveable { mutableStateOf(NotAvailablePopUpVisibility.GONE) }
    if (notAvailablePopupVisibility.isVisible()) {
        NotAvailablePopup { notAvailablePopupVisibility = NotAvailablePopUpVisibility.GONE }
    }
    Row(
        modifier = Modifier
            .background(TelegramBlue40)
            .padding(vertical = 4.dp)
    ) {
        InputIcon(
            onClick = { navController.popBackStack() },
            icon = Icons.Filled.ArrowBack,
            description = stringResource(id = R.string.icon_arrow_description),
            tint = Color.White
        )
        DefaultChatImage(
            Modifier
                .padding(4.dp)
                .size(40.dp)
                .clip(CircleShape)
                .background(uiState.chat.defaultColor), uiState.chat.defaultTitle
        )
        ChatDescription(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            uiState.chat.chatTitle,
            uiState.chat.members,
            uiState.chat.onlineMembers
        )
        InputIcon(
            onClick = { notAvailablePopupVisibility = NotAvailablePopUpVisibility.VISIBLE },
            icon = Icons.Outlined.MoreVert,
            description = stringResource(id = R.string.icon_see_more_description),
            tint = Color.White
        )
    }
}

@Composable
fun ChatDescription(modifier: Modifier, chatTitle: String, members: Int, online: Int) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = chatTitle,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        val membersInfo = when {
            online > 0 -> stringResource(id = R.string.members_and_online_info, members, online)
            else -> stringResource(id = R.string.members_info, members)
        }
        Text(
            text = membersInfo,
            style = MaterialTheme.typography.titleSmall,
            color = TelegramBlue80
        )
    }
}
