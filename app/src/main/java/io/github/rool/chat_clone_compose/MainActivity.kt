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
import androidx.compose.ui.text.input.TextFieldValue
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

enum class InputSelector {
    NONE, EMOJI, ATTACH_FILE, AUDIO_RECORD
}

@Composable
fun MessageComposer() {
    var currentInputSelector by rememberSaveable { mutableStateOf(InputSelector.NONE) }
    val textState = remember { mutableStateOf("") }
    Column {
        Row {
            InputIcon(
                { currentInputSelector = InputSelector.EMOJI },
                Icons.Outlined.AddReaction,
                "emoji icon",
                false
            )
            InputText(textState.value) { textState.value = it }
            InputIcon(
                { currentInputSelector = InputSelector.ATTACH_FILE },
                Icons.Filled.AttachFile,
                "attach file icon",
                false
            )
            InputIcon(
                { currentInputSelector = InputSelector.AUDIO_RECORD },
                Icons.Filled.Mic,
                "audio record icon",
                false
            )
        }
        InputSelector(inputSelector = currentInputSelector)
    }
}

@Composable
fun InputSelector(inputSelector: InputSelector) {
    val label = when (inputSelector) {
        InputSelector.NONE -> ""
        InputSelector.EMOJI -> "Display emoji selector"
        InputSelector.ATTACH_FILE -> "Attach file"
        InputSelector.AUDIO_RECORD -> "Audio record"
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
fun InputText(text: String, onValueChanged: (String) -> Unit) {
    Box {
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
