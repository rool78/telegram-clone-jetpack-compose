package io.github.rool.chat_clone_compose

import androidx.compose.ui.graphics.Color
import io.github.rool.chat_clone_compose.ui.theme.Default1
import io.github.rool.chat_clone_compose.ui.theme.Default2
import io.github.rool.chat_clone_compose.ui.theme.Default3
import io.github.rool.chat_clone_compose.ui.theme.Default4

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
            Message("Frank", Default1, "hey there", "3:03"),
            Message("Oficial Barb", Default2, "hello", "3:04"),
            Message("me", Color.Transparent, "????", "15:27"),
            Message("Jairo", Default3, "What is worng? \uD83D\uDE02", "15:27"),
            Message("me", Color.Transparent, "I do not understand", "15:27"),
            Message("Oficial Barb", Default2, "hello", "15:30"),
            Message("Oficial Barb", Default2, "What's up?", "15:30"),
            Message("Frank", Default1, "Not much, I think you should get a node", "15:33"),
            Message(
                "Frank",
                Default1,
                "Not a leaf, inside that node you have a stack. I know it sounds crazy",
                "15:34"
            ),
            Message("Oficial Barb", Default2, "Ah, ok", "15:40"),
            Message("Frank.klyn", Default1, "I'm bored", "11:43"),
            Message("Frank", Default1, "Any1 there watching match 10?", "1:53"),
            Message("Frank", Default1, "Ding Liren may win", "11:53"),
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
        val mockedUiState: ChatUiState = ChatUiState("TLP Práctica", Default4, 363, 19)
        const val dafaultName: String = "TP"
    }
}
