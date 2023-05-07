package io.github.rool.chatCloneCompose

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
import io.github.rool.chatCloneCompose.core.navigation.ChatCloneScreens
import io.github.rool.chatCloneCompose.core.ui.theme.ChatCloneComposeTheme
import io.github.rool.chatCloneCompose.core.ui.theme.TelegramBlue40
import io.github.rool.chatCloneCompose.features.chatGroup.ChatGroupScreen
import io.github.rool.chatCloneCompose.features.lobby.LobbyScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatCloneComposeTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = TelegramBlue40
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
