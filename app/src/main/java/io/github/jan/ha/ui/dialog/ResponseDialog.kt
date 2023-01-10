package io.github.jan.ha.ui.dialog

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun ResponseDialog(response: String, onDismiss: () -> Unit) {
    Dialog(onDismiss) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = response, modifier = Modifier.padding(10.dp).scrollable(rememberScrollState(), Orientation.Vertical), fontSize = 10.sp)
        }
    }
}