package io.github.rool.chat_clone_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import io.github.rool.chat_clone_compose.components.ChatContent
import io.github.rool.chat_clone_compose.components.ChatToolbar
import io.github.rool.chat_clone_compose.components.MessageComposer
import io.github.rool.chat_clone_compose.ui.theme.ChatclonsecomposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatclonsecomposeTheme {
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
                            messages = Message.mockedMessages
                        )
                        MessageComposer()
                    }
                }
            }
        }
    }
}
