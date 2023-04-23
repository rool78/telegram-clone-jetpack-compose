package io.github.rool.chat_clone_compose

import androidx.compose.ui.graphics.Color
import io.github.rool.chat_clone_compose.ui.theme.TelegramDefault1
import io.github.rool.chat_clone_compose.ui.theme.TelegramDefault2
import io.github.rool.chat_clone_compose.ui.theme.TelegramDefault3
import io.github.rool.chat_clone_compose.ui.theme.TelegramDefault4

data class Message(
    val author: String,
    val authorColor: Color,
    val content: String,
    val timestamp: String,
    //val image: ?TODO add images for some users
) {

    fun isFromAuthor(): Boolean = author == AUTHOR_NAME

    fun toDefaultProfileAuthor(): String {
        val splitAuthor = author.split(" ")
        return if (splitAuthor.size > 1) {
            "${firstCharacterAndUppercase(splitAuthor.first())}${
                firstCharacterAndUppercase(
                    splitAuthor[1]
                )
            }"
        } else {
            firstCharacterAndUppercase(splitAuthor.first())
        }
    }

    private fun firstCharacterAndUppercase(text: String): String =
        text.first().toString().uppercase()

    companion object {
        val mockedMessages: List<Message> = listOf(
            Message("Frank.klyn", TelegramDefault1, "hey there", "3:03"),
            Message("Oficial Barb", TelegramDefault2, "hello", "3:04"),
            Message("me", Color.Transparent, "????", "15:27"),
            Message("Jairo", TelegramDefault3, "What is worng? \uD83D\uDE02", "15:27"),
            Message("me", Color.Transparent, "I do not understand", "15:27"),
            Message("Oficial Barb", TelegramDefault2, "hello", "15:30"),
            Message("Oficial Barb", TelegramDefault2, "What's up?", "15:30"),
            Message(
                "Frank.klyn",
                TelegramDefault1,
                "Not much, I think you should get a node",
                "15:33"
            ),
            Message(
                "Frank.klyn",
                TelegramDefault1,
                "Not a leaf, inside that node you have a stack. I know it sounds crazy",
                "15:34"
            ),
            Message("Oficial Barb", TelegramDefault2, "Ah, ok", "15:40"),
            Message("Frank.klyn", TelegramDefault1, "I'm bored", "11:43"),
            Message("Frank.klyn", TelegramDefault1, "Any1 watching match 10?", "11:53"),
            Message("Frank.klyn", TelegramDefault1, "Ding Liren may win", "11:53"),
        )


        const val AUTHOR_NAME = "me"
    }
}

data class ChatUiState(
    val chatTitle: String,
    val defaultColor: Color,
    val members: Int,
    val onlineMembers: Int,
    val messages: List<Message> = Message.mockedMessages,
) {
    companion object {
        val mockedUiState: ChatUiState = ChatUiState("TLP Práctica", TelegramDefault4, 363, 19)
        const val dafaultName: String = "TP"
    }
}
