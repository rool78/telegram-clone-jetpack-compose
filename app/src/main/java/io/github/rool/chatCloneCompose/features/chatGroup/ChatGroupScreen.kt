package io.github.rool.chatCloneCompose.features.chatGroup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import io.github.rool.chatCloneCompose.features.chatGroup.components.ChatContent
import io.github.rool.chatCloneCompose.features.chatGroup.components.ChatTopBar
import io.github.rool.chatCloneCompose.features.chatGroup.components.MessageComposer

@Composable
fun ChatGroupScreen(navController: NavController, viewModel: ChatGroupViewModel) {
    val uiState = viewModel.chatUiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = { ChatTopBar(navController, uiState.value) }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ChatContent(
                modifier = Modifier.weight(1f),
                messages = uiState.value.chat.messages
            )
            MessageComposer {
                viewModel.sendMessage(it)
                focusManager.clearFocus()
            }
        }
    }
}
