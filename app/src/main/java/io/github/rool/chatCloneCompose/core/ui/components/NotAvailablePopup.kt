package io.github.rool.chatCloneCompose.core.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.rool.chat_clonse_compose.R

enum class NotAvailablePopUpVisibility {
    VISIBLE, GONE
}

fun NotAvailablePopUpVisibility.isVisible(): Boolean =
    this == NotAvailablePopUpVisibility.VISIBLE

@Composable
fun NotAvailablePopup(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Text(
                text = stringResource(id = R.string.not_implemented),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.close))
            }
        }
    )
}
