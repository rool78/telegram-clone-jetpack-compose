package io.github.rool.chat_clone_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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


                }
            }
        }
    }
}

@Composable
fun MessageComposer() {
    TODO()
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
fun InputText() {
    TODO()
}

@Composable
fun TopAppBar(name: String, modifier: Modifier = Modifier) {
    TODO()
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ChatclonsecomposeTheme {
        Row {
            InputIcon({}, Icons.Filled.Face, "emoji icon", true)
            InputIcon({}, Icons.Filled.Face, "emoji icon", false)
            InputIcon({}, Icons.Filled.Build, "emoji icon", false)
        }
    }
}
