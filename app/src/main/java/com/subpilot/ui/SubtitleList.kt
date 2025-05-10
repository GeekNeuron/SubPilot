package com.geekneuron.subpilot.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.geekneuron.subpilot.subtitle.SubtitleEntry

@Composable
fun SubtitleList(
    title: String,
    entries: List<SubtitleEntry>,
    isEditable: Boolean = true,
    onEntryEdited: (Int, String) -> Unit = { _, _ -> }
) {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(entries) { index, item ->
                OutlinedTextField(
                    value = item.text,
                    onValueChange = { new -> onEntryEdited(index, new) },
                    label = { Text("Subtitle #${index + 1}") },
                    readOnly = !isEditable,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
            }
        }
    }
}
