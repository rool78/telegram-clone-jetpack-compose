package io.github.rool.chat_clone_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.github.rool.chat_clone_compose.screens.ChatGroupScreen
import io.github.rool.chat_clone_compose.ui.theme.ChatclonsecomposeTheme
import io.github.rool.chat_clone_compose.ui.theme.TelegramBlue40

class MainActivity : ComponentActivity() {

    private val chatGroupViewModel: ChatGroupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatclonsecomposeTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = TelegramBlue40,
                    )
                }
//                LobbyScreen()
                ChatGroupScreen(chatGroupViewModel) //To have its own fragment
            }
        }
    }
}
