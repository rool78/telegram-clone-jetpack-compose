package io.github.rool.chat_clone_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.rool.chat_clonse_compose.R

@Preview
@Composable
fun ChatToolbarPreview() {
    ChatToolbar()
}

@Composable
fun ChatToolbar() {
    val uiState = ChatUiState.mockedUiState
    Row {
        InputIcon(
            onClick = { /*TODO*/ },
            icon = Icons.Filled.ArrowBack,
            description = stringResource(id = R.string.icon_arrow_description),
            isSelected = false
        )
        Image(
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.group_chat),
            contentDescription = stringResource(id = R.string.icon_group_description)
        )
        ChatDescription(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            uiState.chatTitle,
            uiState.members,
            uiState.onlineMembers
        )
        InputIcon(
            onClick = { /*TODO*/ },
            icon = Icons.Outlined.MoreVert,
            description = stringResource(id = R.string.icon_see_more_description),
            isSelected = false
        )
    }

}

@Composable
fun ChatDescription(modifier: Modifier, chatTitle: String, members: Int, online: Int) {
    Column(
        modifier = modifier
    ) {
        Text(text = chatTitle)
        Text(text = stringResource(id = R.string.members_info, members, online))
    }
}


