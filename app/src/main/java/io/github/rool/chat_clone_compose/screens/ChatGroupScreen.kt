package io.github.rool.chat_clone_compose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.github.rool.chat_clone_compose.MainViewModel
import io.github.rool.chat_clone_compose.components.ChatContent
import io.github.rool.chat_clone_compose.components.ChatToolbar
import io.github.rool.chat_clone_compose.components.MessageComposer
import io.github.rool.chat_clone_compose.ui.theme.Blue40Tg

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatGroupScreen(viewModel: MainViewModel) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Blue40Tg,
        )
    }
    val uiState = viewModel.chatUiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = { ChatToolbar() }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ChatContent(
                modifier = Modifier.weight(1f),
                messages = uiState.value.messages,
            )
            MessageComposer {
                viewModel.sendMessage(it)
                focusManager.clearFocus()
            }
        }
    }
}