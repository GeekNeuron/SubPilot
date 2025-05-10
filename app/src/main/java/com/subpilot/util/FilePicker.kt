package com.geekneuron.subpilot.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState

class FilePicker(private val activity: Activity) {
    fun pickSubtitleFile(onFilePicked: (Uri?) -> Unit): Intent {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("application/x-subrip", "text/plain", "application/octet-stream"))
        return Intent.createChooser(intent, "Select Subtitle File")
    }

    fun pickVideoFile(): Intent {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "video/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("video/mp4", "video/x-matroska"))
        return Intent.createChooser(intent, "Select Video File")
    }
}
