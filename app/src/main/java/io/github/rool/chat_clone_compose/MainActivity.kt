package io.github.rool.chat_clone_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import io.github.rool.chat_clone_compose.core.navigation.ChatCloneScreens
import io.github.rool.chat_clone_compose.core.ui.theme.ChatclonsecomposeTheme
import io.github.rool.chat_clone_compose.core.ui.theme.TelegramBlue40
import io.github.rool.chat_clone_compose.features.chat_group.ChatGroupScreen
import io.github.rool.chat_clone_compose.features.lobby.LobbyScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ChatCloneScreens.Lobby.route
                ) {
                    composable(ChatCloneScreens.Lobby.route) {
                        LobbyScreen(navController)
                    }
                    composable(ChatCloneScreens.ChatGroup.route) {
                        ChatGroupScreen(navController = navController, viewModel = hiltViewModel())
                    }
                }
            }
        }
    }
}
