package io.github.rool.chat_clone_compose

import androidx.compose.ui.graphics.Color
import io.github.rool.chat_clone_compose.ui.theme.Red40
import io.github.rool.chat_clone_compose.ui.theme.Yellow80

data class Message(
    val author: String,
    val authorColor: Color,
    val content: String,
    val timestamp: String,
    //val image: TODO
) {

    fun isFromAuthor(): Boolean = author == AUTHOR_NAME

    companion object {
        val mockedMessages: List<Message> = listOf(
            Message("Frank", Red40, "hey there", "3:03 pm"),
            Message("Oficial Barb", Yellow80, "hello", "3:04 pm"),
            Message("me", Color.Transparent, "????", "3:27 am"),
            Message("Pepito", Color.Magenta, "What is worng?", "3:27 pm"),
            Message("me", Color.Transparent, "I do not understand", "3:27 am"),
            Message("Oficial Barb", Yellow80, "hello", "3:15 pm"),
            Message("Oficial Barb", Yellow80, "What's up?", "3:16 pm"),
            Message("Frank", Red40, "Not much, I think you should get a node", "3:16 pm"),
            Message(
                "Frank",
                Red40,
                "Not a leaf, inside that node you have a stack. I know it sounds crazy",
                "3:27 pm"
            ),
            Message("Oficial Barb", Yellow80, "Ah, ok", "3:27 pm")
        )

        const val AUTHOR_NAME = "me"
    }
}

data class ChatUiState(
    val chatTitle: String,
    val members: Int,
    val onlineMembers: Int,
    val messages: List<Message> = Message.mockedMessages,
) {
    companion object {
        val mockedUiState: ChatUiState = ChatUiState("TLP Project", 363, 19)
    }
}
