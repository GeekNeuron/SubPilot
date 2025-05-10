package com.geekneuron.subpilot.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TimelineEditor(
    label: String,
    timeMs: MutableState<Long>,
    onTimeChanged: (Long) -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = label, style = MaterialTheme.typography.labelMedium)
        Slider(
            value = timeMs.value.toFloat(),
            onValueChange = { onTimeChanged(it.toLong()) },
            valueRange = 0f..600000f, // 0 to 10 minutes
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = formatTime(timeMs.value), style = MaterialTheme.typography.bodySmall)
    }
}

private fun formatTime(ms: Long): String {
    val totalSec = ms / 1000
    val min = totalSec / 60
    val sec = totalSec % 60
    return String.format("%02d:%02d", min, sec)
}
