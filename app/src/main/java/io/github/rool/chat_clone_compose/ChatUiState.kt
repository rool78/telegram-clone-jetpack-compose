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
            Message("Frank", Red40, "hey there", "3:03"),
            Message("Oficial Barb", Yellow80, "hello", "3:04"),
            Message("me", Color.Transparent, "????", "15:27"),
            Message("Pepito", Color.Magenta, "What is worng?", "15:27"),
            Message("me", Color.Transparent, "I do not understand", "15:27"),
            Message("Oficial Barb", Yellow80, "hello", "15:30"),
            Message("Oficial Barb", Yellow80, "What's up?", "15:30"),
            Message("Frank", Red40, "Not much, I think you should get a node", "15:33"),
            Message(
                "Frank",
                Red40,
                "Not a leaf, inside that node you have a stack. I know it sounds crazy",
                "15:34"
            ),
            Message("Oficial Barb", Yellow80, "Ah, ok", "15:40")
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
