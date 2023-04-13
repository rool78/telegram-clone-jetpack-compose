package io.github.rool.chat_clone_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
    NONE, EMOJI, AUDIO_RECORD
}

@Composable
fun MessageComposer() {
    var currentInputSelector by rememberSaveable { mutableStateOf(InputSelector.NONE) }
    val textState = remember { mutableStateOf("") }
    Row {
        InputIcon({ currentInputSelector = InputSelector.EMOJI }, Icons.Filled.Face, "emoji icon", false)
        InputText(textState.value) { textState.value = it }
        InputIcon({ currentInputSelector = InputSelector.AUDIO_RECORD }, Icons.Filled.Face, "attach icon", false)
        InputIcon({ currentInputSelector = InputSelector.AUDIO_RECORD }, Icons.Filled.Build, "audio record icon", false)
    }
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
    BasicTextField(value = text, onValueChange = onValueChanged)
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ChatclonsecomposeTheme {
        Row {
            InputIcon({}, Icons.Filled.Face, "emoji icon", false)
            InputIcon({}, Icons.Filled.Face, "emoji icon", false)
            InputIcon({}, Icons.Filled.Build, "emoji icon", false)
        }
    }
}
