package io.github.rool.chat_clone_compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.rool.chat_clone_compose.Message
import io.github.rool.chat_clone_compose.ui.theme.MessageFromAuthor
import io.github.rool.chat_clone_compose.ui.theme.MessageFromAuthorTimestamp
import io.github.rool.chat_clone_compose.ui.theme.MessageTimestamp
import io.github.rool.chat_clonse_compose.R

@Preview
@Composable
fun ChatContentPreview() {

}

@Composable
fun ChatContent(modifier: Modifier, messages: List<Message>) {
    val listState = rememberLazyListState()
    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(messages.size)
    }
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.tg_wp),
            contentDescription = "chat wallpaper",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        LazyColumn(state = listState) {
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
            DefaultChatProfileImage(
                Modifier
                    .padding(4.dp)
                    .align(Bottom)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(message.authorColor), message.toDefaultProfileAuthor()
            )
        }
        if (isFromAuthor) {
            Spacer(modifier = Modifier.weight(1f))
            MessageFromAuthorBox(message = message, isLastMessage = isLastMessage)
        } else {
            if (!isLastMessage) {
                Spacer(modifier = Modifier.width(48.dp))
            }
            MessageBox(message = message, isFirstMessage, isLastMessage)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun DefaultChatProfileImage(modifier: Modifier, text: String) {
    Box(modifier = modifier) {
        Text(modifier = Modifier.align(Center), text = text)
    }
}

@Composable
fun MessageBox(message: Message, isFirstMessage: Boolean, isLastMessage: Boolean) {
    Surface(
        modifier = Modifier.padding(end = 16.dp, top = 4.dp, bottom = 4.dp),
        color = Color.White,
        shape = RoundedCornerShape(18.dp, 18.dp, 18.dp, if (isLastMessage) 2.dp else 18.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            if (isFirstMessage) {
                Text(
                    style = MaterialTheme.typography.labelLarge,
                    color = message.authorColor,
                    text = message.author
                )
            }
            Row {
                Text(
                    text = message.content, modifier = Modifier
                        .padding(end = 4.dp)
                        .weight(1f, false)
                )
                Text(
                    modifier = Modifier.align(Bottom),
                    style = MaterialTheme.typography.labelSmall,
                    text = message.timestamp,
                    color = MessageTimestamp
                )
            }
        }
    }
}

@Composable
fun MessageFromAuthorBox(message: Message, isLastMessage: Boolean) {
    Surface(
        modifier = Modifier.padding(start = 48.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
        color = MessageFromAuthor,
        shape = RoundedCornerShape(18.dp, 18.dp, if (isLastMessage) 2.dp else 18.dp, 18.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(
                text = message.content, modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f, false)
            )
            Text(
                modifier = Modifier.align(Bottom),
                style = MaterialTheme.typography.labelSmall,
                text = message.timestamp,
                color = MessageFromAuthorTimestamp
            )
        }
    }
}
