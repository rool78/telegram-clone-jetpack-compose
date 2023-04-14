package io.github.rool.chat_clone_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.outlined.AddReaction
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.rool.chat_clone_compose.ui.theme.ChatclonsecomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatclonsecomposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MessageComposer()
                }
            }
        }
    }
}

enum class InputType {
    NONE, EMOJI, ATTACH_FILE, AUDIO_RECORD
}

@Composable
fun MessageComposer() {
    var currentInputType by rememberSaveable { mutableStateOf(InputType.NONE) }
    val textState = remember { mutableStateOf("") }
    Column {
        Row {
            InputIcon(
                { currentInputType = InputType.EMOJI },
                Icons.Outlined.AddReaction,
                "emoji icon",
                false
            )
            InputText(
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                textState.value
            ) { textState.value = it }
            InputIcon(
                { currentInputType = InputType.ATTACH_FILE },
                Icons.Filled.AttachFile,
                "attach file icon",
                false
            )
            InputIcon(
                { currentInputType = InputType.AUDIO_RECORD },
                Icons.Filled.Mic,
                "audio record icon",
                false
            )
        }
        InputSelector(inputType = currentInputType)
    }
}

@Composable
fun InputSelector(inputType: InputType) {
    val label = when (inputType) {
        InputType.NONE -> ""
        InputType.EMOJI -> "Display emoji selector"
        InputType.ATTACH_FILE -> "Attach file"
        InputType.AUDIO_RECORD -> "Audio record"
    }
    Text(text = label)
}

@Composable
fun InputIcon(
    onClick: () -> Unit,
    icon: ImageVector,
    description: String,
    isSelected: Boolean
) {
    val modifier = if (isSelected) {
        Modifier.background(
            color = MaterialTheme.colorScheme.secondary,
            shape = RoundedCornerShape(16.dp)
        )
    } else {
        Modifier
    }
    IconButton(onClick = onClick, modifier = modifier) {
        val tint = if (isSelected) {
            MaterialTheme.colorScheme.onSecondary
        } else {
            MaterialTheme.colorScheme.secondary
        }
        Icon(icon, tint = tint, contentDescription = description)
    }
}

@Composable
fun InputText(modifier: Modifier = Modifier, text: String, onValueChanged: (String) -> Unit) {
    Box(modifier = modifier) { //needed?
        BasicTextField(value = text, onValueChange = onValueChanged)
        if (text.isEmpty()) { //TODO Manage focus
            Text(text = "Mensaje", modifier = Modifier.align(Alignment.BottomStart))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ChatclonsecomposeTheme {
        Row {
            InputIcon({}, Icons.Outlined.AddReaction, "emoji icon", false)
            InputIcon({}, Icons.Filled.AttachFile, "attach file icon", false)
            InputIcon({}, Icons.Filled.Mic, "audio record icon", false)
        }
    }
}
