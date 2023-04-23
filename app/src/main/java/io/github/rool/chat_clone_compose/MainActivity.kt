package io.github.rool.chat_clone_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import io.github.rool.chat_clone_compose.screens.ChatGroupScreen
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
