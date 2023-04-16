package io.github.rool.chat_clone_compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.rool.chat_clone_compose.Message
import io.github.rool.chat_clonse_compose.R

@Preview
@Composable
fun ChatContentPreview() {

}

@Composable
fun ChatContent(modifier: Modifier, messages: List<Message>) {
    LazyColumn(modifier = modifier) {
        for (i in messages.indices) {
            item {
                val nextMessage = messages.getOrNull(i + 1)?.author
                val previousMessage = messages.getOrNull(i - 1)?.author
                val isLastMessage = messages[i].author != nextMessage
                val isFirstMessage = messages[i].author != previousMessage
                Message(messages[i], isLastMessage, isFirstMessage, messages[i].isFromAuthor())
            }
        }
    }
}

@Composable
fun Message(
    message: Message,
    isLastMessage: Boolean,
    isFirstMessage: Boolean,
    isFromAuthor: Boolean
) {
    Row {
        if (isLastMessage && !isFromAuthor) {
            Image(
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)
                    .align(Bottom),
                contentScale = ContentScale.Fit,
                painter = painterResource(id = R.drawable.user_chat),
                contentDescription = stringResource(id = R.string.image_user_description)
            )
        } else {
            Spacer(modifier = Modifier.width(48.dp))
        }
        if (isFromAuthor) {
            MessageFromAuthorBox(message = message, isLastMessage = isLastMessage)
        } else {
            MessageBox(message = message, isFirstMessage, isLastMessage)
        }
    }
}

@Composable
fun MessageBox(message: Message, isFirstMessage: Boolean, isLastMessage: Boolean) {
    Surface(
        modifier = Modifier.padding(4.dp, 4.dp, 48.dp, 4.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(18.dp, 18.dp, 18.dp, if (isLastMessage) 2.dp else 18.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            if (isFirstMessage) {
                Text(
                    style = MaterialTheme.typography.labelLarge,
                    color = message.authorColor,
                    text = message.author
                )
            }
            Row {
                Text(text = message.content, modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.align(Bottom),
                    style = MaterialTheme.typography.labelSmall,
                    text = message.timestamp
                )
            }
        }
    }
}

@Composable
fun MessageFromAuthorBox(message: Message, isLastMessage: Boolean) {
    Surface(
        modifier = Modifier.padding(48.dp, 4.dp, 4.dp, 4.dp),
        color = MaterialTheme.colorScheme.inverseSurface,
        shape = RoundedCornerShape(18.dp, 18.dp, if (isLastMessage) 2.dp else 18.dp, 18.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Text(text = message.content, modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.align(Bottom),
                style = MaterialTheme.typography.labelSmall,
                text = message.timestamp
            )
        }
    }
}
