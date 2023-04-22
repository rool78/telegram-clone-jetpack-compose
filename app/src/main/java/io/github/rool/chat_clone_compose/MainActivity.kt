package io.github.rool.chat_clone_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.rool.chat_clone_compose.components.ChatContent
import io.github.rool.chat_clone_compose.components.ChatToolbar
import io.github.rool.chat_clone_compose.components.MessageComposer
import io.github.rool.chat_clone_compose.ui.theme.ChatclonsecomposeTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatclonsecomposeTheme {
                ChatGroupScreen(mainViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatGroupScreen(viewModel: MainViewModel) {
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
